$(document).ready(function () {
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

    })
});