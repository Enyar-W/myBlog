$('.carousel').carousel({
    interval: 2000
});
$(document).ready(function(){
    var picWidth = $(".newitemCon .top").width();
    $(".newitemCon .top").height(picWidth);
});