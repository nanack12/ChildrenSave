$(document).ready(function() {
  // 변수
  var $header = $('.header');

  $(window).on('scroll', function(){
    if($(this).scrollTop() > 100){
      if(!$header.hasClass('active')){
        $header.addClass('active');
      }
    } else {
      if($header.hasClass('active')){
        $header.removeClass('active');
      }
    }
  });
  $('.slide-group').slick({
    autoplay : true,
    autoplaySpeed : 5000,
    speed : 500,
    dots : true,
  });
});