$('.headrow').click(function(){

    $(this).nextUntil('tr.headrow').slideToggle(10);
});

$(document).ready(function(){
    $('.collapsible').collapsible();
});