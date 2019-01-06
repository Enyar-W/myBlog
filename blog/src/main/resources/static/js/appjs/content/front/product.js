$(document).ready(function () {
    //����ͼ
    var mySwiper = new Swiper ('.swiper-container', {
        direction: 'horizontal',//horizontal
        loop: true,//����ѭ���л�
        pagination: '.swiper-pagination', // ��ҳ��
        mousewheelControl : true,//���������Ƣ�
        mousewheelForceToAxis : true,//���������Ʒ�ҳ����Ϣ�ʹ�ã�
        onInit: function(swiper){ //Swiper2.x�ĳ�ʼ����onFirstInit
            swiperAnimateCache(swiper); //���ض���Ԫ��
            swiperAnimate(swiper); //��ʼ����ɿ�ʼ����
        },
        onSlideChangeEnd: function(swiper){
            swiperAnimate(swiper); //ÿ��slide�л�����ʱҲ���е�ǰslide����
        }
    });
    //�������Ʒ���顱�����û�������ť��
    $("#btnProductshow").click(function(){
        $(this).addClass("hover");
        $("#btnUserMean").removeClass("hover");
        $("#productshow").show();
        $("#userMean").slideUp();
    });
    $("#btnUserMean").click(function(){
        $(this).addClass("hover");
        $("#btnProductshow").removeClass("hover");
        $("#userMean").show();
        $("#productshow").slideUp();
    });
    //等宽
    var picSwiperWidth = $(".swiper-slide").width();
    $(".swiper-slide").height(picSwiperWidth);
});

//���Сͼչʾ��ͼ
function showBigpic(obj){
    var hasClass = $(obj).parent("li").hasClass("hover");
    if(hasClass){
        $(obj).parent("li").removeClass("hover");
        $(obj).parent("li").siblings("li").removeClass("hover");
        $(obj).parents(".smallpic").siblings(".bigpic").hide();
        $(obj).parents(".smallpic").siblings(".bigpic").children("img").attr("src","");
    }else{
        var url = $(obj).attr("src");
        $(obj).parent("li").addClass("hover");
        $(obj).parent("li").siblings("li").removeClass("hover");
        $(obj).parents(".smallpic").siblings(".bigpic").show();
        $(obj).parents(".smallpic").siblings(".bigpic").children("img").attr("src",url);
    }
}