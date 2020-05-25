package com.sxquan.manage.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.common.shiro.LoginFormFilter;
import com.sxquan.manage.common.shiro.ShiroRealm;
import com.sxquan.manage.common.shiro.ShiroSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title ShiroConfig
 * @Description shiro配置
 * @Author sxquan
 * @Date 2019/12/12 17:06
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Autowired
    private EbeProperties properties;

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password:}")
    private String password;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.database:0}")
    private int database;

    /**
     * shiro 中配置 redis 缓存
     * @return RedisManager
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host + ":" + port);
        if (StringUtils.isNotBlank(password)) {
            redisManager.setPassword(password);
        }
        redisManager.setTimeout(timeout);
        redisManager.setDatabase(database);
        return redisManager;
    }

    /**
     * @Description 处理拦截资源文件过滤器
     * @param securityManager
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        log.info("Shiro拦截器工厂类注入开始");

        // 配置shiro安全管理器 SecurityManager
        bean.setSecurityManager(securityManager);
        // todo 添加kickout认证
       Map<String, Filter> hashMap=new LinkedHashMap<>();
       hashMap.put("sessionexp",new LoginFormFilter());
       bean.setFilters(hashMap);

        // 指定要求登录时的链接
        bean.setLoginUrl(properties.getShiro().getLoginUrl());
        // 登录成功后要跳转的链接
        bean.setSuccessUrl(properties.getShiro().getSuccessUrl());
        // 未授权时跳转的界面;
        bean.setUnauthorizedUrl(properties.getShiro().getUnauthorizedUrl());
        // 免认证的url
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getShiro().getAnonUrl(), ",");
        // filterChainDefinitions拦截器map必须用：LinkedHashMap，因为它必须保证有序
        Map<String, String> filterMap = new LinkedHashMap<>();
        for (String anonUrl : anonUrls) {
            filterMap.put(anonUrl,"anon");
        }
        // 配置退出过滤器
        filterMap.put(properties.getShiro().getLogoutUrl(), "logout");
        //  authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问【放行】;user:登录后可认证通过
        filterMap.put("/**", "user");
        filterMap.put("/*", "sessionexp");
        // 添加 shiro 过滤器
        bean.setFilterChainDefinitionMap(filterMap);
        log.info("Shiro拦截器工厂类注入成功");

        return bean;
    }

    /**
     * shiro安全管理器设置realm认证和ehcache缓存管理
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 关联realm
        manager.setRealm(shiroRealm());
        //注入ehcache缓存管理器;
       manager.setCacheManager(cacheManager());
        //注入session管理器;
       manager.setSessionManager(sessionManager());
        //注入Cookie记住我管理器
       manager.setRememberMeManager(rememberMeManager());

        return manager;
    }


    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //设置登录实体的主键
        redisCacheManager.setPrincipalIdFieldName("systemUserId");
        //用户权限信息缓存时间
        // redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }

    /**
     * 3.创建身份认证 Realm
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        realm.setCachingEnabled(true);
        return realm;
    }

    /**
     *  凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码,更改密码生成规则和校验的逻辑一致即可; ）
     * @return org.apache.shiro.authc.credential.HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 散列的次数，比如散列两次，相当于md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }


    /**
     * 开启aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * @Description RememberMe 的功能
     * @return org.apache.shiro.web.servlet.SimpleCookie
     * @Author sxquan
     * @Date 2019/12/12 19:43
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        log.info("记住我，设置cookie过期时间！");
        // 设置 cookie 名称，对应 login.html 页面的 <input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //记住我cookie生效时间单位秒
        cookie.setMaxAge(properties.getShiro().getCookieTimeout());
        // 设置只读模型
        cookie.setHttpOnly(true);
        return cookie;
    }

    /**
     * @Description 配置cookie记住我管理器
     * @return org.apache.shiro.web.mgt.CookieRememberMeManager
     * @Author sxquan
     * @Date 2019/12/12 21:11
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        log.debug("配置cookie记住我管理器！");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AsadiadsUFISdjfksaag=="));
        return cookieRememberMeManager;
    }


    /**
     * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
     * MemorySessionDAO 直接在内存中进行会话维护
     * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     * @return
     */
    @Bean
    public SessionDAO sessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        //session在redis中的保存时间,最好大于session会话超时时间
        // redisSessionDAO.setExpire();
        return redisSessionDAO;
    }


    /**
     * 配置会话ID生成器
     * @return
     */
    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }


    /**
     * 配置会话管理器，设定会话超时及保存
     * @return
     */
    @Bean("sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        //配置监听
        listeners.add( new ShiroSessionListener());
        sessionManager.setSessionListeners(listeners);
        // sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setCacheManager(cacheManager());
        //全局会话超时时间（单位毫秒），默认30分钟
        sessionManager.setGlobalSessionTimeout(properties.getShiro().getSessionTimeout()*1000L);
        //是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过期session 默认为true
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        sessionManager.setSessionValidationInterval(3600000);
        //取消url 后面的 JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }


    /**
     * 并发登录控制
     * @return
     */
//    @Bean
//    public KickoutSessionControlFilter kickoutSessionControlFilter(){
//        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
//        //用于根据会话ID，获取会话进行踢出操作的；
//        kickoutSessionControlFilter.setSessionManager(sessionManager());
//        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
//        kickoutSessionControlFilter.setCacheManager(cacheManager());
//        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
//        kickoutSessionControlFilter.setKickoutAfter(false);
//        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
//        kickoutSessionControlFilter.setMaxSession(1);
//        //被踢出后重定向到的地址；
//        kickoutSessionControlFilter.setKickoutUrl("/login?kickout=1");
//        return kickoutSessionControlFilter;
//    }


    /**
     * 用于开启 Thymeleaf 中的 shiro 标签的使用
     * @return ShiroDialect shiro 方言对象
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}
