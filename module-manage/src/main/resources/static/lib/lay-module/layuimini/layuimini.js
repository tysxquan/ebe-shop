/**
 * date:2019/06/10
 * author:Mr.Chung
 * description:layuimini 框架扩展
 */

layui.define(["element", "jquery", 'table', 'conf','loadBar'], function (exports) {
    var element = layui.element,
        $ = layui.$,
        layer = layui.layer,
        jquery = layui.jquery,
        conf = layui.conf,
        loadBar = layui.loadBar,
        layuiTable = layui.table;



    var logoInfo = {
        title: "ebe-shop",
        image: "images/logo.png",
        href: ""
    };
    var homeInfo = {
        "title": "系统主页",
        "icon": "layui-icon layui-icon-home",
        "href": "/welcome.html?mpi=m-p-i-0"
    };
    // 判断是否在web容器中打开
    if (!/http(s*):\/\//.test(location.href)) {
        return layer.alert("请先将项目部署至web容器（Apache/Tomcat/Nginx/IIS/等），否则部分数据将无法显示");
    }

    layuimini = new function () {

        /**
         *  系统配置
         * @param name
         * @returns {{BgColorDefault: number, urlSuffixDefault: boolean}|*}
         */
        this.config = function (name) {

            var config = {
                urlHashLocation: true,   // URL地址hash定位
                urlSuffixDefault: true, // URL后缀
                BgColorDefault: 0,       // 默认皮肤（0开始）
                checkUrlDefault: false,   // 是否判断URL有效
            };

            if (name === undefined) {
                return config;
            } else {
                return config[name];
            }
        };

        /**
         * 初始化
         * @param url
         */
        this.init = function (url) {
            // layuimini.initBgColor();
            layuimini.initDevice();
            $.getJSON(url, function (res, status) {
                var data = res.data;
                if (res.code !== 200) {
                    layuimini.alert.error(data.message);
                } else {
                    layuimini.initHome(homeInfo);
                    layuimini.initLogo(logoInfo);
                    // layuimini.initClear(data.clearInfo);
                    layuimini.initMenu(data);
                    layuimini.initTab();
                }
            }).fail(function () {
                layuimini.alert.error('菜单接口有误');
            });
            adminLoading.close();
        };
        /**
         * 初始化设备端
         */
        this.initDevice = function () {
            if (layuimini.checkMobile()) {
                $('.layuimini-tool i').attr('data-side-fold', 0);
                $('.layuimini-tool i').attr('class', 'layui-icon layui-icon-spread-left');
                $('.layui-layout-body').attr('class', 'layui-layout-body layuimini-mini');
            }
        };

        /**
         * 初始化首页信息
         * @param data
         */
        this.initHome = function (data) {
            sessionStorage.setItem('layuiminiHomeHref', data.href);
            $('#layuiminiHomeTabId').html('<i class="' + data.icon + '"></i> <span>' + data.title + '</span>' + '<b  class="ebe-tabs-close"></b>');
            $('#layuiminiHomeTabId').attr('lay-id', data.href);
            $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"    src="' + data.href + '"></iframe>');
        };

        /**
         * 初始化logo信息
         * @param data
         */
        this.initLogo = function (data) {
            var html = '<a href="' + data.href + '">\n' +
                '<img  src="' + data.image + '" alt="logo">\n' +
                '<h1>' + data.title + '</h1>\n' +
                '</a>';
            $('.layui-layout-admin .layui-logo').html(html);
        };

        /**
         * 初始化清理缓存
         * @param data
         */
        this.initClear = function (data) {
            $('.layuimini-clear').attr('data-href', data.clearUrl);
        };


        /**
         * 初始化菜单栏
         * @param data
         */
        this.initMenu = function (data) {
            var headerMenuHtml = '',
                headerMobileMenuHtml = '',
                leftMenuHtml = '',
                headerMenuCheckDefault = 'layui-this',
                leftMenuCheckDefault = 'layui-this';
            window.menuParameId = 1;


            leftMenuHtml += '<ul lay-shrink="all" class="layui-nav layui-nav-tree layui-left-nav-tree' + leftMenuCheckDefault + '">\n';

            $.each(data, function (index, menu) {
                headerMenuHtml += '<li class="layui-nav-item ' + headerMenuCheckDefault + '" id="' + index + 'HeaderId" data-menu="' + index + '"> <a href="javascript:;"><i class="' + menu.icon + '"></i> ' + menu.menuName + '</a> </li>\n';
                headerMobileMenuHtml += '<dd><a href="javascript:;" id="' + index + 'HeaderId" data-menu="' + index + '"><i class="layui-icon ' + menu.icon + '"></i> ' + menu.menuName + '</a></dd>\n';
                leftMenuHtml += '<li class="layui-nav-item">\n';
                if (menu.child != undefined && menu.child != []) {
                    leftMenuHtml += '<a href="javascript:;" class="layui-menu-tips" ><i class="layui-icon ' + menu.icon + '"></i><span class="layui-left-nav"> ' + menu.menuName + '</span> </a>';
                    var buildChildHtml = function (html, child, menuParameId) {
                        html += '<dl class="layui-nav-child">\n';
                        $.each(child, function (childIndex, childMenu) {
                            html += '<dd>\n';
                            if (childMenu.child != undefined && childMenu.child != []) {
                                html += '<a href="javascript:;" class="layui-menu-tips" ><i class="layui-icon ' + childMenu.icon + '"></i><span class="layui-left-nav"> ' + childMenu.menuName + '</span></a>';
                                html = buildChildHtml(html, childMenu.child, menuParameId);
                            } else {
                                html += '<a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"  data-tab-mpi="m-p-i-' + menuParameId + '" data-tab="' + childMenu.url + '" target="_self"><i class="layui-icon ' + childMenu.icon + '"></i><span class="layui-left-nav"> ' + childMenu.menuName + '</span></a>\n';
                                menuParameId++;
                                window.menuParameId = menuParameId;
                            }
                            html += '</dd>\n';
                        });
                        html += '</dl>\n';
                        return html;
                    };
                    leftMenuHtml = buildChildHtml(leftMenuHtml, menu.child, menuParameId);
                } else {
                    leftMenuHtml += '<a href="javascript:;" class="layui-menu-tips"  data-type="tabAdd" data-tab-mpi="m-p-i-' + menuParameId + '" data-tab="' + menu.url + '" target="_self"><i class="layui-icon ' + menu.icon + '"></i><span class="layui-left-nav"> ' + menu.menuName + '</span></a>\n';
                    menuParameId++;
                }
                leftMenuHtml += '</li>\n';
            });
            leftMenuHtml += '</ul>\n';
            headerMenuCheckDefault = '';
            leftMenuCheckDefault = 'layui-hide';

            $('.layui-header-pc-menu').html(headerMenuHtml); //电脑
            $('.layui-header-mini-menu').html(headerMobileMenuHtml); //手机
            $('.layui-left-menu').html(leftMenuHtml);
            element.init();
        };

        /**
         * 初始化选项卡
         */
        this.initTab = function () {
            var locationHref = window.location.href;
            var urlArr = locationHref.split("#");
            if (urlArr.length >= 2) {
                var href = urlArr.pop();

                // 判断链接是否有效
                var checkUrl = layuimini.checkUrl(href);
                if (checkUrl != true) {
                    return layuimini.msg_error(checkUrl);
                }

                // 判断tab是否存在
                var checkTab = layuimini.checkTab(href);
                if (!checkTab) {
                    var title = href,
                        tabId = href;
                    $("[data-tab]").each(function () {
                        var checkHref = $(this).attr("data-tab");

                        // 判断是否带参数了
                        if (layuimini.config('urlSuffixDefault')) {
                            if (href.indexOf("mpi=") > -1) {
                                var menuParameId = $(this).attr('data-tab-mpi');
                                if (checkHref.indexOf("?") > -1) {
                                    checkHref = checkHref + '&mpi=' + menuParameId;
                                } else {
                                    checkHref = checkHref + '?mpi=' + menuParameId;
                                }
                            }
                        }

                        if (checkHref == tabId) {
                            title = $(this).html();
                            title = title.replace('style="display: none;"', '');

                            // 自动展开菜单栏
                            var addMenuClass = function ($element, type) {
                                if (type == 1) {
                                    $element.addClass('layui-this');
                                    if ($element.attr('class') != 'layui-nav-item layui-this') {
                                        addMenuClass($element.parent().parent(), 2);
                                    } else {
                                        var moduleId = $element.parent().attr('id');
                                        $(".layui-header-menu li").attr('class', 'layui-nav-item');
                                        $("#" + moduleId + "HeaderId").addClass("layui-this");
                                        $(".layui-left-nav-tree").attr('class', 'layui-nav layui-nav-tree layui-hide');
                                        $("#" + moduleId).attr('class', 'layui-nav layui-nav-tree layui-this');
                                    }
                                } else {
                                    $element.addClass('layui-nav-itemed');
                                    if ($element.attr('class') != 'layui-nav-item layui-nav-itemed') {
                                        addMenuClass($element.parent().parent(), 2);
                                    } else {
                                        var moduleId = $element.parent().attr('id');
                                        $(".layui-header-menu li").attr('class', 'layui-nav-item');
                                        $("#" + moduleId + "HeaderId").addClass("layui-this");
                                        $(".layui-left-nav-tree").attr('class', 'layui-nav layui-nav-tree layui-hide');
                                        $("#" + moduleId).attr('class', 'layui-nav layui-nav-tree layui-this');
                                    }
                                }
                            };
                            addMenuClass($(this).parent(), 1);
                        }
                    });
                    var layuiminiHomeTab = $('#layuiminiHomeTab').attr('lay-id'),
                        layuiminiHomeHref = sessionStorage.getItem('layuiminiHomeHref');

                    // 非菜单打开的tab窗口
                    if (href == title) {
                        var layuiminiTabInfo = JSON.parse(sessionStorage.getItem("layuiminiTabInfo"));
                        if (layuiminiTabInfo != null) {
                            var check = layuiminiTabInfo[tabId];
                            if (check != undefined || check != null) {
                                title = check['title'];
                            }
                        }
                    }

                    if (layuiminiHomeTab != href && layuiminiHomeHref != href) {
                        layuimini.addTab(tabId, href, title, true);
                        layuimini.changeTab(tabId);
                    }
                }
            }
            if (layuimini.config('urlHashLocation')) {
                layuimini.hashTab();
            }
        };


        /**
         * 判断窗口是否已打开
         * @param tabId
         **/
        this.checkTab = function (tabId, isIframe) {
            // 判断选项卡上是否有
            var checkTab = false;
            if (isIframe === undefined || isIframe === false) {
                $(".layui-tab-title li").each(function () {
                    checkTabId = $(this).attr('lay-id');
                    if (checkTabId != null && checkTabId === tabId) {
                        checkTab = true;
                    }
                });
            } else {
                parent.layui.$(".layui-tab-title li").each(function () {
                    checkTabId = $(this).attr('lay-id');
                    if (checkTabId != null && checkTabId === tabId) {
                        checkTab = true;
                    }
                });
            }
            if (checkTab == false) {
                return false;
            }

            // 判断sessionStorage是否有
            var layuiminiTabInfo = JSON.parse(sessionStorage.getItem("layuiminiTabInfo"));
            if (layuiminiTabInfo == null) {
                layuiminiTabInfo = {};
            }
            var check = layuiminiTabInfo[tabId];
            if (check == undefined || check == null) {
                return false;
            }
            return true;
        };

        /**
         * 打开新窗口
         * @param tabId
         * @param href
         * @param title
         */
        this.addTab = function (tabId, href, title, addSession) {
            loadBar.start();
            if (addSession === undefined || addSession === true) {
                var layuiminiTabInfo = JSON.parse(sessionStorage.getItem("layuiminiTabInfo"));
                if (layuiminiTabInfo == null) {
                    layuiminiTabInfo = {};
                }
                layuiminiTabInfo[tabId] = {href: href, title: title}
                sessionStorage.setItem("layuiminiTabInfo", JSON.stringify(layuiminiTabInfo));
            }

            element.tabAdd('layuiminiTab', {
                title: title + '<b data-tab-close="" class="layui-icon layui-unselect layui-icon-close ebe-tabs-close"></b>'
                , content: '<iframe width="100%" height="100%" frameborder="0"  src="' + href + '"></iframe>'
                , id: tabId
            });
            loadBar.finish();
        };

        /**
         * 删除窗口
         * @param tabId
         * @param isParent
         */
        this.delTab = function (tabId, isParent) {
            var layuiminiTabInfo = JSON.parse(sessionStorage.getItem("layuiminiTabInfo"));
            if (layuiminiTabInfo != null) {
                delete layuiminiTabInfo[tabId];
                sessionStorage.setItem("layuiminiTabInfo", JSON.stringify(layuiminiTabInfo))
            }
            if (isParent === true) {
                parent.layui.element.tabDelete('layuiminiTab', tabId);
            } else {
                element.tabDelete('layuiminiTab', tabId);
            }
        };

        /**
         * 在iframe层关闭当前tab方法
         */
        this.closeCurrentTab = function () {
            var ele = $("#top_tabs li.layui-this", parent.document);
            if (ele.length > 0) {
                var layId = $(ele[0]).attr('lay-id');
                layuimini.delTab(layId, true);
            }
        };

        /**
         * 切换选项卡
         **/
        this.changeTab = function (tabId) {
            element.tabChange('layuiminiTab', tabId);
        };

        /**
         * Hash地址的定位
         */
        this.hashTab = function () {
            var layId = location.hash.replace(/^#/, '');
            element.tabChange('layuiminiTab', layId);
            element.on('tab(layuiminiTab)', function (elem) {
                location.hash = $(this).attr('lay-id');
            });
        };

        /**
         * 判断是否为手机
         */
        this.checkMobile = function () {
            var ua = navigator.userAgent.toLocaleLowerCase();
            var pf = navigator.platform.toLocaleLowerCase();
            var isAndroid = (/android/i).test(ua) || ((/iPhone|iPod|iPad/i).test(ua) && (/linux/i).test(pf))
                || (/ucweb.*linux/i.test(ua));
            var isIOS = (/iPhone|iPod|iPad/i).test(ua) && !isAndroid;
            var isWinPhone = (/Windows Phone|ZuneWP7/i).test(ua);
            var clientWidth = document.documentElement.clientWidth;
            if (!isAndroid && !isIOS && !isWinPhone && clientWidth > 768) {
                return false;
            } else {
                return true;
            }
        };

        /**
         * 判断链接是否有效
         * @param url
         * @returns {boolean}
         */
        this.checkUrl = function (url) {
            if (!layuimini.config('checkUrlDefault')) {
                return true;
            }

            var msg = '';
            $.ajax({
                url: url,
                type: 'get',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                async: false,
                error: function (xhr, textstatus, thrown) {
                    msg = 'Status:' + xhr.status + '，' + xhr.statusText + '，请稍后再试！';
                }
            });
            return msg;
        };

        /**
         * 成功
         * @param title
         * @returns {*}
         */
        this.msg_success = function (title) {
            return layer.msg(title, {icon: 1, shade: this.shade, scrollbar: false, time: 2000, shadeClose: true});
        };

        /**
         * 失败
         * @param title
         * @returns {*}
         */
        this.msg_error = function (title) {
            return layer.msg(title, {icon: 2, shade: this.shade, scrollbar: false, time: 3000, shadeClose: true});
        };

        /**
         * 选项卡滚动
         */
        this.tabRoll = function () {
            $(window).on("resize", function (event) {
                var topTabsBox = $("#top_tabs_box"),
                    topTabsBoxWidth = $("#top_tabs_box").width(),
                    topTabs = $("#top_tabs"),
                    topTabsWidth = $("#top_tabs").width(),
                    tabLi = topTabs.find("li.layui-this"),
                    top_tabs = document.getElementById("top_tabs"),
                    event = event || window.event;

                if (topTabsWidth > topTabsBoxWidth) {
                    if (tabLi.position().left > topTabsBoxWidth || tabLi.position().left + topTabsBoxWidth > topTabsWidth) {
                        topTabs.css("left", topTabsBoxWidth - topTabsWidth);
                    } else {
                        topTabs.css("left", -tabLi.position().left);
                    }
                    //拖动效果
                    var flag = false;
                    var cur = {
                        x: 0,
                        y: 0
                    }
                    var nx, dx, x;

                    function down(event) {
                        flag = true;
                        var touch;
                        if (event.touches) {
                            touch = event.touches[0];
                        } else {
                            touch = event;
                        }
                        cur.x = touch.clientX;
                        dx = top_tabs.offsetLeft;
                    }

                    function move(event) {
                        var self = this;
                        if (flag) {
                            window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
                            var touch;
                            if (event.touches) {
                                touch = event.touches[0];
                            } else {
                                touch = event;
                            }
                            nx = touch.clientX - cur.x;
                            x = dx + nx;
                            if (x > 0) {
                                x = 0;
                            } else {
                                if (x < topTabsBoxWidth - topTabsWidth) {
                                    x = topTabsBoxWidth - topTabsWidth;
                                } else {
                                    x = dx + nx;
                                }
                            }
                            top_tabs.style.left = x + "px";
                            //阻止页面的滑动默认事件
                            document.addEventListener("touchmove", function () {
                                event.preventDefault();
                            }, false);
                        }
                    }

                    //鼠标释放时候的函数
                    function end() {
                        flag = false;
                    }

                    //pc端拖动效果
                    topTabs.on("mousedown", down);
                    topTabs.on("mousemove", move);
                    $(document).on("mouseup", end);
                    //移动端拖动效果
                    topTabs.on("touchstart", down);
                    topTabs.on("touchmove", move);
                    topTabs.on("touchend", end);
                } else {
                    //移除pc端拖动效果
                    topTabs.off("mousedown", down);
                    topTabs.off("mousemove", move);
                    topTabs.off("mouseup", end);
                    //移除移动端拖动效果
                    topTabs.off("touchstart", down);
                    topTabs.off("touchmove", move);
                    topTabs.off("touchend", end);
                    topTabs.removeAttr("style");
                    return false;
                }
            }).resize();
        };
        /**
         * 选项卡跳动
         */
        this.rollPage = function (e, i) {
            let t = parent.layui.jquery("#top_tabs"),
                n = t.children("li"),
                l = (t.prop("scrollWidth"), t.outerWidth()),
                s = parseFloat(t.css("left"));
            if ("left" === e) {
                if (!s && s <= 0) return;
                var r = -s - l;
                n.each(function (e, i) {
                    var n = jquery(i),
                        l = n.position().left;
                    if (l >= r) return t.css("left", -l), !1
                })
            } else "auto" === e ? !function () {
                var e, r = n.eq(i);
                if (r[0]) {
                    if (e = r.position().left, e < -s) return t.css("left", -e);
                    if (e + r.outerWidth() >= l - s) {
                        var o = e + r.outerWidth() - (l - s);
                        n.each(function (e, i) {
                            var n = jquery(i),
                                l = n.position().left;
                            if (l + s > r.outerWidth() && l - s > o) return t.css("left", -l), !1
                        })
                    }
                }
            }() : n.each(function (e, i) {
                var n = jquery(i),
                    r = n.position().left;
                if (r + n.outerWidth() >= l - s) return t.css("left", -r), !1
            })
        };
        this.leftPage = function () {
            layuimini.rollPage("left")
        };

        this.rightPage = function () {
            layuimini.rollPage()
        };
        /**
         *  数据表封装
         */
        this.table = {};
        this.table.init = function (params) {
            let defaultSetting = {
                cellMinWidth: 80,
                page: true,
                skin: 'line',
                limit: 10,
                limits: [5, 10, 20, 30, 40, 50],
                autoSort: true,
                request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'
                },
                parseData: function (res) {
                    return {
                        "code": res.code === 200 ? 0 : res.code,
                        "count": res.data.total,
                        "data": res.data.list
                    }
                }
            };
            return layuiTable.render(
                $.extend({}, defaultSetting, params)
            );
        };
        /**
         * 检测连接是否有效
         * @param str
         * @returns {boolean}
         */
        this.isUrl = function (str) {
            return /^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/.test(
                str
            )
        };
        // 模型框
        this.model = {};
        this.model.viewMax = function (title, url, params) {
            params = $.extend({
                url: url,
                move:false,
                maxmin: false,
                scrollbar: false,
                skin:'ebe-layer-modelMax layui-layer-ebe',
                shadeClose: false,
                title: [
                    (title || '请填写头部信息'),
                    'font-size:16px;color:#666666;line-height:56px;height:56px;padding:0 50px;background-color:#fff;'
                ],
                area: ["100%","100%"]
            }, params);
            layuimini.popup(params);
        };
        this.model.view = function (title, url, params) {
            params = $.extend({
                url: url,
                maxmin: false,
                shadeClose: false,
                title: [
                    title,
                    'font-size:15px;color:#08132b;line-height:46px;height:48px;padding-bottom:0;background-color:#fff;border-bottom:none'
                ],
                area: $(window).width() <= 750 ? ['80%', '80%'] : ['50%', '60%']
            }, params);
            layuimini.popup(params);
        };
        this.model.openMax = function (title, url, params) {
            params = $.extend({
                url: url,
                move:false,
                maxmin: false,
                scrollbar: false,
                skin:'ebe-layer-modelMax layui-layer-ebe',
                shadeClose: false,
                title: [
                    (title || '请填写头部信息'),
                    'font-size:16px;color:#666666;line-height:56px;height:56px;padding:0 50px;background-color:#fff;'
                ],
                area: ["100%","100%"]
            }, params);
            layuimini.popup(params);
        };
        this.model.open = function (title, url, params) {
            params = $.extend({
                url: url,
                maxmin: true,
                shadeClose: false,
                title: [
                    (title || '请填写头部信息'),
                    'font-size:16px;color:#08132b;line-height:46px;padding-bottom:0;border-bottom:1px solid #fcfcfc;background-color:#fcfcfc'
                ]
            }, params);
            layuimini.popup(params);
        };
        this.popup = function (params) {
            var url = params.url || '';
            var success = params.success || function () {
            };
            // POPUP_DATA = params.data || {};
            params.skin = params.skin || 'layui-layer-ebe';
            var defaultParams = {
                type: 1,
                area: $(window).width() <= 750 ? ['90%', '90%'] : ['60%', '90%'],
                shadeClose: true,
                zIndex:900,
            };

            if (layuimini.isUrl(url)) {
                params.type = 2;
                params.content = url;
                layer.open($.extend(defaultParams, params));
                return;
            }

            layuimini.SkipUrl(url, function (res) {
                var htmlElem = $('<div>' + res.html + '</div>');

                if (params.title === undefined) {
                    params.title = htmlElem.find('title').text() || '信息';
                    if (params.title) htmlElem.find('title').remove()
                }
                params.success = function (layer, index) {
                    success(layer, index);
                };
                params.content = htmlElem.html();
                params = $.extend(defaultParams, params);
                layer.open($.extend(defaultParams, params));
            });


        };
        /**
         *  异步获取页面内容
         * @param type 请求类型
         * @param url
         * @param data
         * @returns {boolean}
         */
        this.SkipUrl = function (url, callback) {
            url = url || conf.entry;
            loadBar.start();
            $.ajax({
                url: url,
                type: 'get',
                dataType: 'html',
                success: function (r) {
                    var result;
                    try {
                        result = JSON.parse(r);
                    } catch (e) {
                        result = r;
                    }

                    if (result.code === 401) {
                        layuimini.notify('登录失效', '登录已失效，请重新登录', function () {
                            window.parent.location.reload();
                            window.parent.location.hash = '';
                        });
                        loadBar.finish();
                        return;
                    }
                    if (result.code === 403) {
                        layuimini.changeError('/403',"403","无权限");
                        loadBar.finish();
                        return;
                    }
                    if (result.code === 404) {
                        layuimini.changeError("/404", '404');
                        loadBar.finish();
                        return;
                    }
                    if (result.code === 500) {
                        layuimini.changeError('/500','500');
                        loadBar.finish();
                        return;
                    }
                    callback({html: r, url: url});
                    loadBar.finish();
                },
                error: function (res) {
                    if (res.status === 404) {
                        layuimini.changeError("/404", '404',"页面不存在");
                    }
                    if (res.status === 403) {
                        layuimini.changeError('/403','403',"无权限");
                    }
                    if (res.status === 500) {
                        layuimini.changeError("/500", '500',"系统出错");
                    }
                    console.log('请求视图文件异常，' + '状态：' + res.status);
                    loadBar.error();
                    return false;
                }
            });
        };
        //在Iframe子页面里打开tab
        this.openIframeTab = function (href, tabId,title) {
            let pageNum;
            // 拼接参数
            if (href.indexOf("?") > -1) {
                href = href + '&mpi=' + 'm-p-i-' + tabId;
                tabId = href;
            } else {
                href = href + '?mpi=' + 'm-p-i-' + tabId;
                tabId = href;
            }
            if (tabId === null || tabId === undefined) {
                tabId = new Date().getTime();
            }
            // 判断该窗口是否已经打开过
            let checkTab = layuimini.checkTab(tabId, true);
            if (!checkTab) {
                pageNum = $("#top_tabs>li", parent.document).length - 1;
                let layuiminiTabInfo = JSON.parse(sessionStorage.getItem("layuiminiTabInfo"));
                if (layuiminiTabInfo == null) {
                    layuiminiTabInfo = {};
                }
                layuiminiTabInfo[tabId] = {href: href, title: title};
                sessionStorage.setItem("layuiminiTabInfo", JSON.stringify(layuiminiTabInfo));
                parent.layui.element.tabAdd('layuiminiTab', {
                    title: title + '<b data-tab-close="" class="layui-icon layui-unselect layui-icon-close ebe-tabs-close"></b>'
                    , content: '<iframe width="100%" height="100%" frameborder="0"  src="' + href + '"></iframe>'
                    , id: tabId
                });
            }
            parent.layui.element.tabChange('layuiminiTab', tabId);
            if (pageNum === null || pageNum === undefined) {
                pageNum = $("#top_tabs .layui-this", parent.document).index();
            }

            layuimini.initDevice();
            layuimini.rollPage('auto', pageNum+1);
        };
        this.changeError = function (url, tabId,title) {
            let href = url;
            title = '<i class="layui-icon layui-icon-setting"></i><span class="layui-left-nav"> '+title+'</span>';

            if (tabId === null || tabId === undefined) {
                tabId = '500';
                title = '<i class="layui-icon layui-icon-setting"></i><span class="layui-left-nav"> 系统出错</span>'
            }
            layuimini.openIframeTab(href,tabId,title);
        };

        this.modal = {};

        this.modal.base = function (msg, params) {
            params = params || {};
            params.titleIcoColor = params.titleIcoColor || '#5a8bff';
            params.titleIco = params.titleIco || 'exclaimination';
            params.title = params.title || [
                '<i class="layui-icon layui-icon-' +
                params.titleIco +
                '" style="font-size:12px;background:' +
                params.titleIcoColor +
                ';display:inline-block;position:relative;top:-2px;height:21px;line-height:21px;text-align:center;width:21px;color:#fff;border-radius:50%;margin-right:12px;"></i>' +
                '<span style="letter-spacing:0.05em">'+ params.titleValue+'</span>',
                'background:#fff;border:none;font-weight:bold;font-size:16px;color:#08132b;padding-top:10px;height:36px;line-height:46px;padding-bottom:0;'
            ];
            params = $.extend(
                {
                    skin: "layui-layer-ebe layui-layer-ebe-modal",
                    area: [$(window).width() <= 750 ? '60%' : '400px'],
                    closeBtn: 0,
                    shadeClose: false
                }, params);
            layer.alert(msg, params);
        };
        this.notify = function (title, msg, yes, params) {
            params = params || {};
            params.titleIco = 'exclaimination';
            params.titleIcoColor = '#ffc107';
            params.titleValue = title;
            params.shadeClose = false;
            params = $.extend({
                btn: ['确定']
                , yes: function (index, layero) {
                    yes && (yes)();
                    layer.close(index);
                }
            }, params);
            layuimini.modal.base(msg, params);
        };
        // ----------------- 模态框类 --------------------- //

        this.modal.confirm = function (title, msg, yes, no, params) {
            params = params || {};
            params.titleIco = 'exclaimination';
            params.titleIcoColor = '#ffc107';
            params.titleValue = title;
            params.shadeClose = false;
            params = $.extend({
                btn: ['确定', '取消']
                , yes: function (index, layero) {
                    yes && (yes)();
                    layer.close(index);
                }
                , btn2: function (index, layero) {
                    no && (no)();
                }
            }, params);
            layuimini.modal.base(msg, params);
        };
        this.modal.info = function (title, msg, yes, params) {
            params = params || {};
            params.titleIco = 'infomation';
            params.titleIcoColor = '#2db7f5';
            params.titleValue = title;
            params.shadeClose = false;
            params = $.extend({
                btn: ['确定']
                , yes: function (index, layero) {
                    yes && (yes)();
                    layer.close(index);
                }
            }, params);
            layuimini.modal.base(msg, params);
        };

        this.modal.warn = function (title, msg, yes, params) {
            params = params || {};
            params.titleIco = 'exclaimination';
            params.titleIcoColor = '#ffc107';
            params.titleValue = title;
            params.shadeClose = false;
            params = $.extend({
                btn: ['确定']
                , yes: function (index, layero) {
                    yes && (yes)();
                    layer.close(index);
                }
            }, params);
            layuimini.modal.base(msg, params);
        };

        this.modal.success = function (title, msg, yes, params) {
            params = params || {};
            params.titleIco = 'ok';
            params.titleIcoColor = '#30d180';
            params.titleValue = title;
            params.shadeClose = false;
            params = $.extend({
                btn: ['确定']
                , yes: function (index, layero) {
                    yes && (yes)();
                    layer.close(index);
                }
            }, params);
            layuimini.modal.base(msg, params);
        };

        this.modal.error = function (title, msg, yes, params) {
            params = params || {};
            params.titleIco = 'close';
            params.titleIcoColor = '#ff5652';
            params.titleValue = title;
            params.shadeClose = false;
            params = $.extend({
                btn: ['确定']
                , yes: function (index, layero) {
                    yes && (yes)();
                    layer.close(index);
                }
            }, params);
            layuimini.modal.base(msg, params);
        };
        // ----------------- 弹窗类 --------------------- //

        this.alert = {};
        this.alert.loading = function(){
            index = layer.load(2, {time: conf.layer_load_time});
        };
        this.alert.closeLoading = function () {
            layer.close(index);
        };
        function alertParams(msg, params) {
            params.time = 3500;
            params.shade = 0;
            params.btn = null;
            params.title = [
                '<i class="layui-icon layui-icon-' +
                params.titleIco +
                '" style="font-size:12px;background:' +
                params.titleIcoColor +
                ';display:inline-block;font-weight:600;position:relative;top:-2px;height:21px;line-height:21px;text-align:center;width:21px;color:#fff;border-radius:50%;margin-right:12px;"></i>' +
                '<span style="letter-spacing:0.05em">'+(msg || '请填写提示信息')+'</span>',
                'background:#fff;border:none;font-weight:500;font-size:14px;color:#08132b;margin-bottom:-50px;padding:16px;height:45px;line-height:14px;padding-bottom:0;'
            ];
            params.offset = '40px';
            params.area = [$(window).width() <= 750 ? '80%' : '400px'];
        }

        this.alert.success = function (msg, params) {
            params = params || {};
            params.titleIco = 'ok';
            params.titleIcoColor = '#30d180';
            alertParams(msg, params);
            layuimini.modal.base('', params);
        };
        this.alert.warn = function (msg, params) {
            params = params || {};
            params.titleIco = 'exclaimination';
            params.titleIcoColor = '#ffc107';
            alertParams(msg, params);
            layuimini.modal.base('', params);
        };
        this.alert.error = function (msg, params) {
            params = params || {};
            params.titleIco = 'close';
            params.titleIcoColor = '#ff5652';
            alertParams(msg, params);
            layuimini.modal.base('', params);
        };
        this.alert.info = function (msg, params) {
            params = params || {};
            params.titleIco = 'infomation';
            params.titleIcoColor = '#2db7f5';
            alertParams(msg, params);
            layuimini.modal.base('', params);
        };
        // 判断 a种的属性是否 b都有，并且弱相等
        this.nativeEqual = function (a, b) {
            var aProps = Object.getOwnPropertyNames(a);
            var bProps = Object.getOwnPropertyNames(b);
            for (var i = 0; i < aProps.length; i++) {
                var propName = aProps[i];
                if (!compare(a[propName], b[propName])) {
                    return false;
                }
            }
            return true;
        };

        function compare(a, b) {
            if (a === '' && (b === undefined || b === null)) {
                return true;
            } else if ((a === undefined || a === null) && b === '') {
                return true;
            } else {
                return a == b;
            }
        }

        /**
         * 判断删除操作table当前应该跳转的页码
         * @param tableId 表格id名
         * @param removeNum 需要删除的数目
         */
        this.judgeRemoveTablePage = function (tableId,removeNum){
            //当前页码值
            let curr = $(".layui-laypage-skip").find("input").val();
            let len = layuiTable.cache[tableId].length;
            if (curr > 1 && len === removeNum) {
                return curr - 1;
            }
            return curr;
        };

        /**
         * 判断新增操作table当前应该跳转的页码
         * @param tableId 表格id名
         */
        this.judgeAddTablePage = function (tableId) {
            let currTable = layuiTable.reload(tableId);
            let pageTemp = currTable.config.page;
            return Math.ceil((pageTemp.count + 1) / pageTemp.limit);
        };

        /**
         * url截取图片名字
         * @param url
         * @returns {string}
         */
        this.subImageName = function (url) {
            if (url === ""){
                return "";
            }
            if (url !== undefined) {
                var urlArr = url.split(",");
                for (var i = 0; i < urlArr.length; i++) {
                    urlArr[i] = urlArr[i].substring(urlArr[i].lastIndexOf("/")+1);
                }
                return urlArr.toString();
            }
            return url;
        };
        //封装 ajax get请求
        this.get = function (url, params, success) {
            let index;
            $.ajax({
                url: url,
                type: 'GET',
                data: params,
                beforeSend: function () {
                    index = layer.load(2, {time: conf.layer_load_time});
                },
                success: function (r) {
                    resolveResponse(r, success, index);
                },
                error: function (r) {
                    var res = {
                        code:r.status,
                        message: r.message
                    }
                    resolveResponse(res, null,index);
                }
            });
        };
        //封装 ajax post请求
        this.post = function (url, params, success) {
            let index;
            $.ajax({
                url: url,
                type: 'POST',
                data: params,
                beforeSend: function () {
                    index = layer.load(2, {time: conf.layer_load_time});
                },
                success: function (r) {
                    resolveResponse(r, success, index);
                },
                error: function (r) {
                    var res = {
                        code:r.status,
                        message: r.message
                    }
                    resolveResponse(res, null,index);
                }
            });

        };
        //封装 ajax put请求
        this.put = function (url, params, success) {
            let index;
            $.ajax({
                url: url,
                type: 'PUT',
                data: params,
                beforeSend: function () {
                    index = layer.load(2, {time: conf.layer_load_time});
                },
                success: function (r) {
                    resolveResponse(r, success, index);
                },
                error: function (r) {
                    var res = {
                        code:r.status,
                        message: r.message
                    }
                    resolveResponse(res, null,index);
                }
            });
        };
        //封装 ajax delete请求
        this.delete = function (url, success) {
            let index;
            $.ajax({
                url: url,
                type: 'DELETE',
                beforeSend: function () {
                    index = layer.load(2, {time: conf.layer_load_time});
                },
                success: function (r) {
                    resolveResponse(r, success, index);
                },
                error: function (r) {
                    var res = {
                        code:r.status,
                        message: r.message
                    }
                    resolveResponse(res, null,index);
                }
            });
        };

        //通用回调
        function resolveResponse(r, f, index) {
            setTimeout(function () {
                layer.close(index);
                if (r.code === 200) {
                    f(r) && (f)();
                } else if (r.code === 401) {
                    layuimini.modal.info('登录失效', '登录已失效，请重新登录', function () {
                        window.parent.location.reload();
                        window.location.hash = '';
                });
                } else if (r.code === 403) {
                    layuimini.alert.warn('对不起，您暂无该操作权限');
                } else {
                    layuimini.alert.error(r.message ? r.message : '操作失败');
                    console.error(r);
                }
            }, conf.load_delay_time);
        }
        //开启表格自动高度调节
        this.openAutoFixed =function (tableElem) {
            autoFixed(tableElem.next());
            // 监听浏览器窗口大小变化
            let resizeTimer;
            $(window).resize(function() {
                if (resizeTimer) {
                    clearTimeout(resizeTimer);
                }
                resizeTimer = setTimeout(function() {
                        resizeTimer = null;
                        autoFixed();
                    }, 120);
            });
            // 监听表头鼠标按下事件
            $(document).on('mousedown', 'thead',
                function(e) {
                    let that = $(this);
                    $(document).one('mouseup',
                        function() {
                            autoFixed(that.parents(".layui-table-view"));
                        });
                });
        };
        /**
         * 修正表格浮动栏高度
         * @param tableElem 表格显示div
         */
        function autoFixed(tableElem) {
            var $tableView = tableElem || $(".layui-table-view");
            var dataIndex ,trHeight;

            $tableView.each(function() {
                // 获取两侧浮动栏
                var $fixed = $(this).find(".layui-table-fixed");
                // 同步表头高度
                $fixed.find(".layui-table-header tr").height($(this).find(".layui-table-header tr").eq(0).height());
                // 遍历tr 修正浮动栏行高
                $(this).find(".layui-table-main tr").each(function() {
                    dataIndex = $(this).attr("data-index");
                    trHeight = $(this).css("height");
                    $fixed.find("tr[data-index=" + dataIndex + "]").css("height", trHeight);
                })
            });
        }

        /**
         *  在子页面打开窗口
         * @param tabId
         * @param href
         * @param title
         */
        this.changeIframeTab = function (title, href) {
            loadBar.start();
            let tabId = 1204;
            parent.layui.$(".layui-tab-title li").each(function () {
                let checkTabId = $(this).attr('lay-id');
                if (checkTabId != null && checkTabId.substring(checkTabId.lastIndexOf("=")+1) === 'm-p-i-'+tabId) {
                    layuimini.delTab(checkTabId,true);
                }
            });
            title = '<i class="layui-icon layui-icon-mr"></i><span class="layui-left-nav">'+" " +title+'</span>';
            layuimini.openIframeTab(href,tabId,title);
            loadBar.finish();
        };

        // 文件下载
        this.download = function (url, params, fileName) {
            loadBar.start();
            url += '?' + parseParams(params);
            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.responseType = "blob";
            xhr.onload = function () {
                if (this.status === 200) {
                    loadBar.finish();
                    var fileType = this.response.type;
                    var blob = this.response;
                    var reader = new FileReader();
                    reader.readAsDataURL(blob);
                    reader.onload = function (e) {
                        if ('msSaveOrOpenBlob' in navigator) { // IE，Edge
                            var base64file = e.target.result + '';
                            window.navigator.msSaveOrOpenBlob(createFile(base64file.replace('data:' + fileType + ';base64,', ''), fileType), fileName);
                        } else { // chrome，firefox
                            var link = document.createElement('a');
                            link.style.display = 'none';
                            link.href = e.target.result;
                            link.setAttribute('download', fileName);
                            document.body.appendChild(link);
                            link.click();
                            $(link).remove();
                        }
                    }
                } else {
                    loadBar.error();
                    layuimini.alert.error('下载失败');
                }
            };
            xhr.send();
        };
        function parseParams(param, key, encode) {
            if (param == null) return '';
            var arr = [];
            var t = typeof (param);
            if (t === 'string' || t === 'number' || t === 'boolean') {
                arr.push(key + '=' + ((encode == null || encode) ? encodeURIComponent(param) : param));
            } else {
                for (var i in param) {
                    var k = key == null ? i : key + (param instanceof Array ? '[' + i + ']' : '.' + i);
                    arr.push(parseParams(param[i], k, encode));
                }
            }
            return arr.join("&");
        }
        // 解析 BASE64文件内容 for IE，Edge
        function createFile(urlData, fileType) {
            var bytes = window.atob(urlData),
                n = bytes.length,
                u8arr = new Uint8Array(n);
            while (n--) {
                u8arr[n] = bytes.charCodeAt(n);
            }
            return new Blob([u8arr], {type: fileType});
        }
    };
    /**
     * 关闭选项卡
     **/
    $('body').on('click', '[data-tab-close]', function () {
        $parent = $(this).parent();
        tabId = $parent.attr('lay-id');
        if (tabId !== undefined || tabId !== null) {
            layuimini.delTab(tabId);
        }
        layuimini.rollPage('auto', $("#top_tabs .layui-this").index() - 1);
        var layId = $(".layui-tab-title .layui-this").attr("lay-id"),
        $leftMenu =$ (".layui-left-menu .layui-nav");
        $leftMenu.find("")
    });

    /**
     * 打开新窗口
     */
    $('body').on('click', '[data-tab]', function () {
        var tabId = $(this).attr('data-tab'),
            href = $(this).attr('data-tab'),
            title = $(this).html(),
            target = $(this).attr('target');
        if (target === '_blank') {
            layer.close(loading);
            window.open(href, "_blank");
            return false;
        }
        title = title.replace('style="display: none;"', '');
        // 拼接参数
        if (layuimini.config('urlSuffixDefault')) {
            var menuParameId = $(this).attr('data-tab-mpi');
            if (href.indexOf("?") > -1) {
                href = href + '&mpi=' + menuParameId;
                tabId = href;
            } else {
                href = href + '?mpi=' + menuParameId;
                tabId = href;
            }

        }

        if (tabId === null || tabId === undefined) {
            tabId = new Date().getTime();
        }
        var pageNum;
            // 判断该窗口是否已经打开过
            var checkTab = layuimini.checkTab(tabId);
            if (!checkTab) {
                layuimini.addTab(tabId, href, title, true);
                pageNum = $("#top_tabs>li").length - 1;
            }
            element.tabChange('layuiminiTab', tabId);
            if (pageNum === null || pageNum === undefined) {
                pageNum = $("#top_tabs .layui-this").index();
            }
            layuimini.initDevice();
            layuimini.rollPage('auto', pageNum);
    });

    /**
     * 在iframe子菜单上打开新窗口
     */
    $('body').on('click', '[data-iframe-tab]', function () {
        var tabId = $(this).attr('data-iframe-tab'),
            href = $(this).attr('data-iframe-tab'),
            icon = $(this).attr('data-icon'),
            title = $(this).attr('data-title'),
            target = $(this).attr('target'),
            pageNum;

        if (target == '_blank') {
            parent.layer.close(loading);
            window.open(href, "_blank");
            return false;
        }
        title = '<i class="' + icon + '"></i><span class="layui-left-nav">  ' + title + '</span>';
        if (tabId == null || tabId == undefined) {
            tabId = new Date().getTime();
        }
        // 判断该窗口是否已经打开过
        var checkTab = layuimini.checkTab(tabId, true);
        if (!checkTab) {
            loadBar.start();
            pageNum = $("#top_tabs>li").length - 1;
            var layuiminiTabInfo = JSON.parse(sessionStorage.getItem("layuiminiTabInfo"));
            if (layuiminiTabInfo == null) {
                layuiminiTabInfo = {};
            }
            layuiminiTabInfo[tabId] = {href: href, title: title}
            sessionStorage.setItem("layuiminiTabInfo", JSON.stringify(layuiminiTabInfo));
            parent.layui.element.tabAdd('layuiminiTab', {
                title: title + '<b data-tab-close="" class="layui-icon layui-unselect layui-icon-close ebe-tabs-close"></b>'
                , content: '<iframe width="100%" height="100%" frameborder="0"  src="' + href + '"></iframe>'
                , id: tabId
            });
            loadBar.finish();
        }
        parent.layui.element.tabChange('layuiminiTab', tabId);
        if (pageNum == null || pageNum == undefined) {
            pageNum = $("#top_tabs .layui-this").index();
        }
        layuimini.rollPage('auto', pageNum);
    });


    /**
     * 左侧菜单的切换
     */
    $('body').on('click', '[data-menu]', function () {
        var loading = layer.load(2, {shade: false, time: 2 * 1000}),
        $parent = $(this).parent(),
        menuId = $(this).attr('data-menu');

        // header
        $(".layui-header-menu .layui-nav-item.layui-this").removeClass('layui-this');
        $(this).addClass('layui-this');
        // left
        $(".layui-left-menu .layui-nav.layui-nav-tree.layui-this").addClass('layui-hide');
        $(".layui-left-menu .layui-nav.layui-nav-tree.layui-this.layui-hide").removeClass('layui-this');
        $("#" + menuId).removeClass('layui-hide');
        $("#" + menuId).addClass('layui-this');
        layer.close(loading);
    });

    /**
     * 清理
     */
    $('body').on('click', '[data-clear]', function () {
        var loading = layer.load(2, {shade: false, time: 2 * 1000});
        sessionStorage.clear();

        // 判断是否清理服务端
        var clearUrl = $(this).attr('data-href');
        if (clearUrl != undefined && clearUrl != '' && clearUrl != null) {
            $.getJSON(clearUrl, function (data, status) {
                layer.close(loading);
                if (data.code != 1) {
                    return layuimini.msg_error(data.msg);
                } else {
                    return layuimini.msg_success(data.msg);
                }
            }).fail(function () {
                layer.close(loading);
                return layuimini.msg_error('清理缓存接口有误');
            });
        } else {
            layer.close(loading);
            return layuimini.msg_success('清除缓存成功');
        }
    });

    /**
     * 刷新
     */
    $('body').on('click', '[data-refresh]', function () {
        loadBar.start();
        $(".layui-tab-item.layui-show").find("iframe")[0].contentWindow.location.reload();
        loadBar.finish();
    });

    /**
     * 选项卡操作
     */
    $('body').on('click', '[data-page-close]', function () {
        var loading = layer.load(2, {shade: false, time: 2 * 1000});
        var closeType = $(this).attr('data-page-close');
        $(".layui-tab-title li").each(function () {
            tabId = $(this).attr('lay-id');
            var id = $(this).attr('id');
            if (id !== 'layuiminiHomeTabId') {
                var tabClass = $(this).attr('class');
                if (closeType === 'all') {
                    layuimini.delTab(tabId);
                } else if (closeType === 'other') {
                    if (tabClass !== 'layui-this') {
                        layuimini.delTab(tabId);
                    }
                } else {
                    if (tabClass === 'layui-this') {
                        layuimini.delTab(tabId);
                    }
                }
            }
        });
        $(".ebe-tabs-down dl .layui-this").removeClass('layui-this');
        $(".ebe-tabs-down .layui-nav-child").removeClass('layui-show');
        if (closeType === 'all') {
            layuimini.rollPage('left');
        } else if (closeType === 'other') {
            layuimini.rollPage('left');
        } else {
            layuimini.rollPage('auto', $("#top_tabs .layui-this").index() - 1);
        }
        layer.close(loading);
    });

    /**
     * 菜单栏缩放
     */
    $('body').on('click', '[data-side-fold]', function () {
        var loading = layer.load(2, {shade: false, time: 2 * 1000});
        var isShow = $(this).attr('data-side-fold');
        if (isShow == 1) { // 缩放
            $(this).attr('data-side-fold', 0);
            $('.layuimini-tool i').attr('class', 'layui-icon layui-icon-spread-left');
            $('.layui-layout-body').attr('class', 'layui-layout-body layuimini-mini');
        } else { // 正常
            $(this).attr('data-side-fold', 1);
            $('.layuimini-tool i').attr('class', 'layui-icon layui-icon-shrink-right');
            $('.layui-layout-body').attr('class', 'layui-layout-body layuimini-all');
        }
        element.init();
        layer.close(loading);
    });

    /**
     * 监听提示信息
     */
    $("body").on("mouseenter", ".layui-menu-tips", function () {
        var classInfo = $(this).attr('class'),
            tips = $(this).children('span').text(),
            isShow = $('.layuimini-tool i').attr('data-side-fold');
        if (isShow == 0) {
            //修改提醒框颜色
            openTips = layer.tips(tips, $(this), {tips: [2, '#20222A'], time: 30000});
        }
    });
    $("body").on("mouseleave", ".layui-menu-tips", function () {
        var isShow = $('.layuimini-tool i').attr('data-side-fold');
        if (isShow == 0) {
            try {
                layer.close(openTips);
            } catch (e) {
                console.log(e.message);
            }
        }
    });





    $('body').on("click", "*[admin-event]", function () {
        var e = jquery(this),
            i = e.attr("admin-event");

        if (i === 'leftPage') {
            layuimini.leftPage();
        } else {
            layuimini.rightPage();
        }
    });

    exports("layuimini", layuimini);
});
