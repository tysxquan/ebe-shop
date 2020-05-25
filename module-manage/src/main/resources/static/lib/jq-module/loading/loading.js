var load_options = {
	time: 100,
	content: "admin..."
};
!function (content, options) {
	function templateFun(options) {
		return `<div class="loading">
						<div class="ball-loader">
							<span></span><span></span><span></span><span></span>
						</div>
				</div>`;
	}

	function headerInit(content, options) {
		options = options || {};
		if (typeof content == "string") {
			options["content"] = content || load_options.content;
		} else if (typeof content == "object") {
			options = content;
		}
		options.time = options.time || load_options.time;
		options.content = options.content || load_options.content;
		return options;
	}

	load_options = headerInit(content, options);
	var template = templateFun(load_options);
	document.writeln(template);
}();

var adminLoading = {
	close: function (time, dom) {
		time = time || load_options.time;
		dom = dom || document.getElementsByClassName("loading")[0];
		var interval = window.setInterval(function () {
			if (document.readyState==="complete"){
				// 页面加载状态完全结束时，执行的动画
				dom.classList.add("close");
				dom.parentNode.removeChild(dom);/**删除当前节点*/
				clearInterval(interval);
			}
		},time);


	}
};


