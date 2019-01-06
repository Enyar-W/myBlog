$(document).ready(function () {
    var mySwiper = new Swiper ('.swiper-container', {
        direction: 'horizontal',//horizontal
        loop: true,//无限循环切换
        pagination: '.swiper-pagination', // 分页器
        mousewheelControl : true,//开启鼠标控制①
        mousewheelForceToAxis : true,//鼠标滚动控制翻页（配合①使用）
        onInit: function(swiper){ //Swiper2.x的初始化是onFirstInit
            swiperAnimateCache(swiper); //隐藏动画元素
            swiperAnimate(swiper); //初始化完成开始动画
        },
        onSlideChangeEnd: function(swiper){
            swiperAnimate(swiper); //每个slide切换结束时也运行当前slide动画
        }

    })
});