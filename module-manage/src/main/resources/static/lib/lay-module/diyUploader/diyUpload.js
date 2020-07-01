/* 
 *	jQuery文件上传插件,封装UI,上传处理操作采用Baidu WebUploader;
 */
layui.define(['jquery', 'adminLayer', 'webUploader', 'photoSwipe'], function (exports) {
    var $ = layui.jquery,
        webUploader = layui.webUploader;
        adminLayer = layui.adminLayer,
        CLS_UL = "upload-ul",
        uploadBtn = '<i class="layui-icon layui-icon-addition"></i>';
    //引入css
    layui.link(layui.cache.base + "/diyUploader/diyUpload.css");
    layui.link(layui.cache.base + "/PhotoSwipe/default-skin/default-skin.css");
    layui.link(layui.cache.base + "/PhotoSwipe/photoswipe.css");

    /**
     * 获取默认配置
     */
    function getConfig() {
        return {
            //按钮容器;
            pick: {
                //默认不允许多文件上传
                multiple: false,
                label: uploadBtn,
            },
            //开启即刻上传
            auto: true,
            //类型限制;
            accept: {
                title: "Images",
                extensions: "gif,jpg,jpeg,bmp,png",
                mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'
            },
            //自定义初始化文件路径
            initFileUrl: "",
            //删除请求地址
            deleteUrl: "",
            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            //文件上传方式
            method: "POST",
            //上传前不压缩！
            compress: false,
            //上传并发数。允许同时最大上传进程数
            threads: 2,
            // 不压缩！
            resize: false,
            //服务器地址;
            server: "",
            //是否已二进制的流的方式发送文件，这样整个上传内容php://input都为文件内容
            sendAsBinary: false,
            // 开起分片上传。 thinkphp的上传类测试分片无效,图片丢失;
            // chunked:true,
            // 分片大小
            // chunkSize:512 * 1024,
            //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
            fileNumLimit: 10,
            fileSizeLimit: 100000 * 1024,
            //默认限制上传大小2M
            fileSingleSizeLimit: 2000 * 1024,
            imageName:"imageName",
        }
    }


    /*
     *	上传方法 opt为参数配置;
     *	serverCallBack回调函数 每个文件上传至服务端后,服务端返回参数,无论成功失败都会调用 参数为服务器返回信息;
     */
    self.diyUpload = function (opt, serverCallBack) {
        if (typeof opt != "object") {
            alert('参数错误!');
            return;
        }
        if (opt.pick.label) {
            opt.pick.label = uploadBtn + '<div style="margin-top: 8px;font-size: 12px;">' + opt.pick.label + '</div>';
        }
        //获取默认配置
        let config = getConfig();
        $.extend(true, config, opt);


        let $fileInput = config.pick.id;

        //组装参数;
        if (config.success) {
            var successCallBack = config.success;
            delete config.success;
        }

        if (config.error) {
            var errorCallBack = config.error;
            delete config.error;
        }


        let webUploader = getUploader(config);

        if (!WebUploader.Uploader.support()) {
            adminLayer.alert.error(' 上传组件不支持您的浏览器！');
            return false;
        }


        //绑定文件加入队列事件;
        webUploader.on('fileQueued', function (file) {
            createBox($fileInput, file, webUploader, config);
        });
        //进度条事件
        webUploader.on('uploadProgress', function (file, percentage) {
            var $fileBox = $('#' + file.id);
            var $diyBar = $fileBox.find('.diyBar');
            $diyBar.css("display", "flex");
        });

        //全部上传结束后触发;
        webUploader.on('uploadFinished', function () {
            $fileInput.next('.parentFileBox').children('.diyButton').remove();
        });
        //绑定发送至服务端返回后触发事件;
        webUploader.on('uploadAccept', function (object, data) {
            if (serverCallBack) serverCallBack(data);
        });

        //上传成功后触发事件;
        webUploader.on('uploadSuccess', function (file, response) {
            let $fileBox = $('#' + file.id),
                $diyBar = $fileBox.find('.diyBar'),
                data = response.data;
            if (response.code === 200) {
                //淡出加载效果
                $diyBar.fadeOut(800);
                file.name = data.imageName;
                //显示图标
                $fileBox.find('.viewThumb').append('<img src="' + data.url + '" >');
                if (successCallBack) {
                    successCallBack(response);
                }
            } else {
                $diyBar.find('i').css("color", "red");
                adminLayer.alert.error(response.message);
                $fileBox.fadeOut(2000, function () {
                    webUploader.removeFile(file.id, true);
                });
            }

        });

        //上传失败后触发事件
        webUploader.on('uploadError', function (file, reason) {
            let $fileBox = $('#' + file.id);
            let $diyBar = $fileBox.find('.diyBar');
            $diyBar.find('i').css("color", "red");
            adminLayer.alert.error("上传失败，错误信息: " + reason);
            $fileBox.fadeOut(2000, function () {
                webUploader.removeFile(file.id, true);
            });

            let err = {code: reason, name: file.name};
            if (errorCallBack) {
                errorCallBack(err);
            }
        });


        //当文件被移除队列后触发。
        webUploader.on("fileDequeued", function (file) {
            config.pick.id.fadeIn(500);
            //刷新按否则按钮无效
            webUploader.refresh();
        });

        //选择文件错误触发事件;
        webUploader.on('error', function (code) {
            let messages = '';
            switch (code) {
                case  'F_DUPLICATE' :
                    messages = '该文件已经被选择了!';
                    break;
                case  'Q_EXCEED_NUM_LIMIT' :
                    messages = '上传文件数量超过限制!';
                    break;
                case  'F_EXCEED_SIZE' :
                    messages = '文件大小超过限制!';
                    break;
                case  'Q_EXCEED_SIZE_LIMIT' :
                    messages = '所有文件总大小超过限制!';
                    break;
                case 'Q_TYPE_DENIED' :
                    messages = '文件类型不正确或者是空文件!';
                    break;
                default :
                    messages = '未知错误!';
                    break;
            }
            adminLayer.alert.error(messages);
        });

        //初始化图片注意加载顺序
        if (config.initFileUrl !== "") {
            let files = initImg(config.initFileUrl);
            webUploader.addFiles(files);
            layui.each(webUploader.getFiles('complete'), function (index, file) {
                let $fileBox = $('#' + file.id);
                $fileBox.find('.viewThumb').append('<img src="' + file.source.url + '" >');
            });
        }

        return webUploader;
    };

        /**
         * 初始化图片
         * @param imageUrls 图片链接（数组形式）
         */
        function initImg(imageUrls) {
            if (imageUrls === undefined || imageUrls === "") {
                return;
            }
            let files = [];
            if ($.isArray(imageUrls)) {
                layui.each(imageUrls, function (index, item) {
                    addFile(files, item);
                });
            } else {
                addFile(files, imageUrls);
            }
            return files;
        }

        /**
         * 模仿添加文件
         * @param files 文件数组
         * @param imageUrls 传入内容
         */
        function addFile(files, imageUrls) {
            let obj = {};
            obj.name = imageUrls.substring(imageUrls.lastIndexOf("/") + 1);
            obj.lastModifiedDate = new Date();
            obj.url = imageUrls;
            obj.size = 1024;
            let file = new WebUploader.File(obj);
            file.setStatus('complete');
            files.push(file);
        }

    /**
     * 加载photoSwip以及初始化
     * @param items 需要预览的图片集
     * @param index 默认打开第几张
     */
    function openPhotoSwipe(items,index) {
            initPhotoSwipeHtml();
            var pswpElement = document.querySelectorAll('.pswp')[0];

            var options = {
                index: index,
                bgOpacity: 0.8,
            };
            var gallery = new PhotoSwipe(pswpElement, PhotoSwipeUI_Default, items, options);
            gallery.init();
            gallery.listen('close', function() {
                pswpElement.remove()
            });
        };


        //实例化Web Uploader
        function getUploader(opt) {
            return new WebUploader.Uploader(opt);
        }


        //取消事件;
        function removeLi($li, file_id, webUploader) {
            webUploader.removeFile(file_id,true);
            $li.remove();
        }


        //创建文件操作div;
        function createBox($fileInput, file, webUploader, config) {
            var file_id = file.id,
                $parentFileBox = $fileInput.parent().find("." + CLS_UL),
                liLength = $parentFileBox.find('li').length;

            //达到最大队列数移除上传按钮
            if (config.fileNumLimit === 1) {
                config.pick.id.hide();
            } else if (config.fileNumLimit === liLength + 1) {
                config.pick.id.fadeOut(800);
            }
            //设置排序字段
            file.sort = Number(liLength + 100);

            //添加子容器;
            let li = '<li id="' + file_id + '" > \
					<div class="viewThumb">\
					    <input type="hidden">\
					    <div class="diyBar"> \
						<i class="layui-icon layui-icon-loading-1 layui-icon layui-anim layui-anim-rotate layui-anim-loop "></i>\
					    </div> \
						<div class="control">\
						<i class="layui-icon layui-icon-close del"></i>\
						<div class="control-bottom"><span class="diyLeft"><i class="layui-icon layui-icon-left"></i></span><span class="diyCancel"><i class="layui-icon layui-icon-eye"></i></span><span class="diyRight"><i class="layui-icon layui-icon-right"></i></span></div>\
						</div>\
					</div> \
				</li>';

            $parentFileBox.append(li);

            let $fileBox = $parentFileBox.find('#' + file_id);

            //绑定取消事件;
            let $del = $fileBox.find('.del').on('click', function () {
                let imageName = webUploader.getFile(file_id).name;

                adminLayer.modal.confirm("删除图片", "是否确定删除该图片", function () {
                    $.ajax({
                        url: config.deleteUrl === "" ? config.server : config.deleteUrl,
                        data: config.imageName+"="+imageName,
                        type: 'DELETE',
                        success: function (r) {
                            if (r.code === 200) {
                                $fileBox.fadeOut(1000, function () {
                                    removeLi($fileBox, file, webUploader);
                                });
                            } else {
                                adminLayer.alert.error(r.message);
                            }

                        }
                    });
                });
            });
            if (config.fileNumLimit === 1) {
                $parentFileBox.find(".diyLeft").remove();
                $parentFileBox.find(".diyRight").remove();
            }

            //文件队列排序
            function sortNumber() {
                webUploader.sort(function (obj1, obj2) {
                    return Number(obj1.sort - obj2.sort);
                });

            }

            //绑定左移事件;
            $fileBox.find('.diyLeft').on('click', function () {
                var $fileBoxPrev = $fileBox.prev();
                if ($fileBoxPrev.length > 0) {
                    $fileBox.insertBefore($fileBoxPrev);
                    var this_file = webUploader.getFile(file_id),
                        prev_file = webUploader.getFile($fileBoxPrev.attr("id"));
                    this_file.sort = Number(this_file.sort - 1);
                    prev_file.sort = Number(prev_file.sort + 1);
                    //更新排序
                    sortNumber();
                }
            });
            //绑定右移事件;
            $fileBox.find('.diyRight').on('click', function () {
                var $fileBoxNext = $fileBox.next();
                if ($fileBoxNext.length > 0) {
                    $fileBox.insertAfter($fileBoxNext);
                    var this_file = webUploader.getFile(file_id),
                        next_file = webUploader.getFile($fileBoxNext.attr("id"));
                    this_file.sort = Number(this_file.sort + 1);
                    next_file.sort = Number(next_file.sort - 1);
                    sortNumber();
                }

            });

            //绑定查看预览图事件;
            $fileBox.find(".diyCancel").on('click', function () {
                var items = [];
                layui.each($parentFileBox.find("li img"),function (i,t) {
                    var item = {
                        src:t.src,
                        w:t.naturalWidth,
                        h:t.naturalHeight
                    };
                    items.push(item)
                });
                //获取当前位置
                openPhotoSwipe(items,$fileBox.index());
            });

            if (file.type.split("/")[0] !== 'image') {
                var liClassName = getFileTypeClassName(file.name.split(".").pop());
                $fileBox.addClass(liClassName);
                return;
            }
        }

        //获取文件类型;
        function getFileTypeClassName(type) {
            var fileType = {};
            var suffix = '_diy_bg';
            fileType['pdf'] = 'pdf';
            fileType['ppt'] = 'ppt';
            fileType['doc'] = 'doc';
            fileType['docx'] = 'doc';
            fileType['jpg'] = 'jpg';
            fileType['zip'] = 'zip';
            fileType['rar'] = 'rar';
            fileType['xls'] = 'xls';
            fileType['xlsx'] = 'xls';
            fileType['txt'] = 'txt';
            fileType = fileType[type] || 'ppt';
            return fileType + suffix;
        }

        function initPhotoSwipeHtml() {
            var html = '<div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">\
                          <div class="pswp__bg"></div>\
                        <div class="pswp__scroll-wrap">\
                    <div class="pswp__container">\
                    <div class="pswp__item"></div>\
                        <div class="pswp__item"></div>\
                        <div class="pswp__item"></div>\
                        </div>\
                        <div class="pswp__ui pswp__ui--hidden">\
                        <div class="pswp__top-bar">\
                    <div class="pswp__counter"></div>\
                        <button class="pswp__button pswp__button--close" title="关闭"></button>\
                        <button class="pswp__button pswp__button--fs" title="全屏"></button>\
                        <button class="pswp__button pswp__button--zoom" title="放大/缩小"></button>\
                        <div class="pswp__preloader">\
                        <div class="pswp__preloader__icn">\
                        <div class="pswp__preloader__cut">\
                        <div class="pswp__preloader__donut"></div>\
                        </div>\
                        </div>\
                        </div>\
                        </div>\
                        <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">\
                        <div class="pswp__share-tooltip"></div> \
                        </div>\
                        <button class="pswp__button pswp__button--arrow--left" title="上一张">\
                        </button>\
                        <button class="pswp__button pswp__button--arrow--right" title=下一张">\
                        </button>\
                        <div class="pswp__caption">\
                        <div class="pswp__caption__center"></div>\
                        </div>\
                        </div>\
                        </div>';
            $('body').append(html);
        }

    exports('diyUploader', self)
});