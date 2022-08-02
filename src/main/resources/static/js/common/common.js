
//contextpath 구하기
function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf("/", hostIndex + 1) );
};

//로딩화면 설정
function loading(){
    var maskHeight = $(document).height();
    var maskWidth = window.document.body.clientWidth;
    var mask = $("<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>");
    var box1 = $('<div class="d-flex justify-content-center">');
    var box2 = $('<div class="spinner-border" role="status">');
    var box3 = $('<span class="visually-hidden">Loading...</span>');
    box2.append(box3);
    box1.append(box2);
    mask.append(box1);
    $('body').append(mask);
    $('#mask').css({
        'width' : maskWidth,
        'height': maskHeight,
        'opacity' : '0.7'
    });
    $('#mask').css({
        'display': 'flex',
        'justify-content': 'center',
        'align-items': 'center'
    });
    //show loading
    //$("#mask").show();
    //hide loading
    //$("#mask").hide();
}
