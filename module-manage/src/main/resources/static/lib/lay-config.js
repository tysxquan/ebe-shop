/**
 * date:2019/08/16
 * author:Mr.Chung
 * description:此处放layui自定义扩展
 */

window.rootPath = (function (src) {
    src = document.scripts[document.scripts.length - 1].src;
    return src.substring(0, src.lastIndexOf("/") + 1);
})();

layui.config({
    base: rootPath + "lay-module/",
    version: true
}).extend({
    layuimini: "layuimini/layuimini", // layuimini扩展
    step: 'step-lay/step', // 分步表单扩展
    treeTable: 'treeTable/treeTable', //table树形扩展
    tableSelect: 'tableSelect/tableSelect', // table选择扩展
    iconPicker: 'iconPicker/iconPickerFont', // 图标选择扩展
    echarts: 'echarts/echarts.min', // echarts图表扩展
    echartsTheme: 'echarts/echartsTheme', // echarts图表主题扩展
    wangEditor: 'wangEditor/wangEditor', // wangEditor富文本扩展
	sliderVerify:'sliderVerify/sliderVerify', //滑动解锁
	dropdown: 'dropdown/dropdown',//下拉选项
	formSelects: 'formSelects/formSelects-v4.min',
    view: 'admin/view', //通用异步请求
    conf: "admin/config", //全局变量
    adminLayer: "admin/adminLayer",//封装layer
	validate: 'admin/validate',
    eleTree: 'eleTree/eleTree',//eleTree树组件
    treeSelect: 'treeSelect/treeSelect',//下拉选择树
    openTable: 'openTable/openTable', //下拉表格
    loadBar: 'admin/loadBar',
    scrollTop: 'admin/scrollTop',
    diyUploader: 'diyUploader/diyUpload',
    ajaxCascader: 'ajaxCascader/ajaxCascader',
    xmSelect: 'xmSelect/xm-select',
    selectY: 'selectY/selectY',
    selectPlus: 'admin/selectPlus',
    countUp: 'admin/CountUp',
    webUploader: 'diyUploader/webuploader.min',
    photoviewer: 'diyUploader/photoviewer',
    photoSwipeUI: 'PhotoSwipe/photoswipe-ui-default',
    photoSwipe: 'PhotoSwipe/photoswipe',
});
