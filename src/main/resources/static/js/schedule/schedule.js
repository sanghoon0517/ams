function resetForm(){
    $("#c_st_dt").val(null);
    $("#c_ed_dt").val(null);
    $("#c_st_tm").val(null);
    $("#c_ed_tm").val(null);
    $('#c_st_tm').attr('disabled', false);
    $('#c_ed_tm').attr('disabled', false);
    for (var k = 0; k < $(".wkd").length; k++) {
        $(".wkd").eq(k).attr("disabled", true);
        $(".wkd").eq(k).attr("checked", false);
    }
    $("#c_nm").val(null);
    $("#c_color").val('#000000');
    $("#c_nm").val(null);
    $("select#t_idx option[value="+null+"]").attr('selected',true);
}
$(function(){
    
     //colorpicker 라이브러리 초기화
    $("#colorpicker").spectrum({
        color: "#d9d2e9"
    });
    // 요일선택 비활성화
    for(var k=0;k<$(".wkd").length;k++){
        $(".wkd").eq(k).attr("disabled",true);
        $(".wkd").eq(k).attr("checked",false);
    }
    let data = {};
    let today = new Date();   

    let year = today.getFullYear(); // 년도
    let month = today.getMonth() + 1;  // 월
    let date = today.getDate();  // 날짜
    let day = today.getDay();  // 요일

    // 종일 일정 스위치 클릭시 동작
    $("#allDay").on('change', function () {
        if ($(this).is(':checked')) {
            $(this).attr('value', 'true');
            $("#allDayText").text('종일 일정');
            $('#c_st_tm').attr('disabled',true);
            $('#c_ed_tm').attr('disabled',true);
            $('#c_st_tm').val(null);
            $('#c_ed_tm').val(null);
        }
        else {
            $(this).attr('value', 'false');
            $("#allDayText").text('종일 일정 아님');
            $('#c_st_tm').attr('disabled',false);
            $('#c_ed_tm').attr('disabled',false);
        }
    });
    // 반복 일정 스위치 클릭시 동작
    $("#repeatDay").on('change', function () {
        if ($(this).is(':checked')) {
            $(this).attr('value', 'true');
            $("#repeatDayText").text('반복 일정');
            for(var k=0;k<$(".wkd").length;k++){
                $(".wkd").eq(k).attr("disabled",false);
            }
        }
        else {
            $(this).attr('value', 'false');
            $("#repeatDayText").text('반복 일정 아님');
            for(var k=0;k<$(".wkd").length;k++){
                $(".wkd").eq(k).attr("disabled",true);
                $(".wkd").eq(k).attr("checked",false);
            }
        }
    });
    // 클래스 선택시 클래스 정보 복사
    $("#c_idx").on('change', function () {
        var c_idx = $("option:selected", this).attr("value");
        var selectedClass = [];
        let allClass = [[${#lists.size(class)}]];
        [# th:each="class : ${class}"]
            if([[${class.c_idx}]]==c_idx){
                //일정제목
                $("#c_nm").val([[${class.c_nm}]]);
                //표시 컬러
                $("#c_color").val([[${class.c_color}]]);
                //담당 선생
                $("select#t_idx option[value="+[[${class.t_idx}]]+"]").attr('selected',true);
                // 일정 요일
                var wkd = [[${class.c_wkd}]];
                $(".wkd").attr("checked",false);
                if($("#repeatDay").is(":checked")){
                        if(wkd.length!=0){
                            for(var j=0;j<wkd.length;j++){
                                var temp ="";
                                if(wkd[j]=='0'){
                                    temp = 'sun';
                                }else if(wkd[j]=='1'){
                                    temp = 'mon';
                                }else if(wkd[j]=='2'){
                                    temp = 'tue';
                                }else if(wkd[j]=='3'){
                                    temp = 'wed';
                                }else if(wkd[j]=='4'){
                                    temp = 'thur';
                                }else if(wkd[j]=='5'){
                                    temp = 'fri';
                                }else if(wkd[j]=='6'){
                                    temp = 'sat';
                                }
                                $('#'+temp).attr("checked",true);
                            }
                        }
                }
                
                // 일정 시간 c_st_tm
                $("#c_st_tm").val([[${class.c_st_tm}]]);
                $("#c_ed_tm").val([[${class.c_ed_tm}]]);
                // 수강중인 원생
            }
        [/]
    });

    //일정 등록
    $('#schedule-register').click(function(){
        //반복 일정 여부에 따라
        var data ={};
        if($("#repeatDay").val()=='true'){
            var weekdays = [];
            for(var k=0;k<$(".wkd").length;k++){
                if($(".wkd").eq(k).is(":checked")){
                    weekdays.push($(".wkd").eq(k).val());
                }
            }
            data ={
                s_idx : $('#s_idx').val(),
                routine: true,
                title : $("#c_nm").val(),
                c_idx : $('select#c_idx option:selected').val(),
                t_idx : $('select#t_idx option:selected').val(),
                color : $("#c_color").val(),
                startRecur: $("#c_st_dt").val(),
                endRecur: $("#c_ed_dt").val(),
                startTime: $("#allDay").val() == 'true' ? null : $("#c_st_tm").val(),
                endTime: $("#allDay").val() == 'true' ?  null : $("#c_ed_tm").val(),
                daysOfWeek: weekdays,
                allDay: $("#allDay").val()
            }
        }else{
            data ={
                s_idx : $('#s_idx').val(),
                routine: false,
                title : $("#c_nm").val(),
                c_idx : $('select#c_idx option:selected').val(),
                t_idx : $('select#t_idx option:selected').val(),
                color : $("#c_color").val(),
                start : $("#allDay").val() == 'true' ?  $("#c_st_dt").val() : $("#c_st_dt").val()+"T"+$("#c_st_tm").val(),
                end   : $("#allDay").val() == 'true' ?  $("#c_ed_dt").val() : $("#c_ed_dt").val()+"T"+$("#c_ed_tm").val(),
                allDay : $("#allDay").val()
            }
        }
        
        $.ajax({
            type: "POST",
            url: getContextPath() + "/schedule",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("일정등록 완료");
            location.href = getContextPath() + "/schedule";
        }).fail(function (error) {
            alert("일정등록 실패");
        });   
    });

    function calendar_init(date_type){
        let draggableEl = document.getElementById('mydraggable');
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView : date_type,
            locale: 'ko', // 한국어 설정
            customButtons: {
                myCustomButton: {
                text: '일정등록',
                click: function() {

                    $('#register-schedule').modal('show');
                    
                }
                }
            },
            headerToolbar: {
                right: 'prev,next today myCustomButton',
                left: 'title',
                center: 'dayGridMonth,timeGridWeek,timeGridDay'
            },
            dayMaxEvents: true, // 일정개수 이상의 이벤트시 팝오버 생김
            selectable: true, 
            editable: true,
            droppable: true,  // 드래그 가능
            expandRows: true, 
            drop: function(arg) {
            
                alert("hi")
            },
            eventClick: function(info) { //이벤트 클릭시 이벤트 정보 출력 이벤트
                var eventObj = info.event;		
                alert('Clicked ' + eventObj.start + eventObj.end + eventObj.title + eventObj.id);
                $.ajax({
                    type: "GET",
                    url: getContextPath() + "/schedule/"+eventObj.id+"/post",
                    contentType: "application/json;charset=utf-8",
                    dataType: "json"
                }).done(function (resp) {
                    alert("일정 정보 로딩 완료");
                    resetForm();
                    console.log(resp.data);
                    var schedule = resp.data;
                    // 값들 모달에 이동
                    //일정 번호
                    $('#s_idx').val(schedule.s_idx);
                    //클래스정보
                    $("select#c_idx option[value="+schedule.c_idx+"]").attr('selected',true);
                    //일정 제목
                    $("#c_nm").val(schedule.title);
                    //담당 선생님
                    $("select#t_idx option[value="+schedule.t_idx+"]").attr('selected',true);
                    //반복여부
                    $("#repeatDay").attr("checked",schedule._repeat);
                    
                    //종일여부
                    $("#allDay").attr("checked",schedule.allDay);
                    //컬러
                    $("#c_color").val(schedule.color);
                    //요일
                    for (var i = 0; i < schedule.daysOfWeek.length; i++) {
                        var wkd = schedule.daysOfWeek;
                        var temp = "";
                        if (wkd[i] == '0') {
                            temp = 'sun';
                        } else if (wkd[i] == '1') {
                            temp = 'mon';
                        } else if (wkd[i] == '2') {
                            temp = 'tue';
                        } else if (wkd[i] == '3') {
                            temp = 'wed';
                        } else if (wkd[i] == '4') {
                            temp = 'thur';
                        } else if (wkd[i] == '5') {
                            temp = 'fri';
                        } else if (wkd[i] == '6') {
                            temp = 'sat';
                        }
                        $('#' + temp).attr("checked", true);
                    }
                    //일정 시작일
                    if(schedule.routine==true){
                        $('#repeatDay').attr('checked', true);
                        $("#repeatDayText").text('반복 일정');
                        $("#c_st_dt").val(schedule.startRecur);
                        $("#c_ed_dt").val(schedule.endRecur);
                        $("#c_st_tm").val(schedule.startTime);
                        $("#c_ed_tm").val(schedule.endTime);
                        for (var k = 0; k < $(".wkd").length; k++) {
                            $(".wkd").eq(k).attr("disabled", false);
                        }
                    }else{
                        if(schedule.allDay ==true){
                            $('#repeatDay').attr('checked', false);
                            $("#repeatDayText").text('반복 일정 아님');
                            $('#repeatDay').attr('checked', false);
                            $('#allDay').attr('value', true);
                            $("#allDayText").text('종일 일정');
                            $("#c_st_dt").val(schedule.startRecur);
                            $("#c_ed_dt").val(schedule.endRecur);
                            $('#c_st_tm').val(null);
                            $('#c_ed_tm').val(null);
                            $('#c_st_tm').attr('disabled',true);
                            $('#c_ed_tm').attr('disabled',true);

                        }else{
                            $('#allDay').attr('value', false);
                            $("#allDayText").text('종일 일정 아님');
                            var st_dt= String(schedule.start).split('T');
                            var ed_dt= String(schedule.end).split('T');
                            $('#c_st_tm').attr('disabled',false);
                            $('#c_ed_tm').attr('disabled',false);
                            $("#c_st_dt").val(st_dt[0]);
                            $("#c_ed_dt").val(ed_dt[0]);
                            $("#c_st_tm").val(st_dt[1]);
                            $("#c_ed_tm").val(ed_dt[1]);
                        }
                    }
                            $('#register-schedule').modal('show'); 
                }).fail(function (error) {
                });   

             },
            eventChange: function(obj){ // 드래그 수정시 발생 이벤트
                alert(obj.event.title);
                alert(obj.event.id);
                alert(obj.event.start);
                alert(obj.event.end);
                alert(obj.event.daysOfWeek);
                console.log(obj.oldEvent);
                console.log(obj.event);
                console.log(obj);
                
            },
             select: function(arg) { // 칸 클릭시 발생하는 추가 이벤트
                var title = prompt('Event Title:');
                console.log("hi");
                if (title) {
                    calendar.addEvent({
                        title: title,
                        start: arg.start,
                        end: arg.end,
                        allDay: arg.allDay
                    });
                    calendar.unselect();
            }
        },

        eventSources: [{
            events: 
                function(info, successCallback, failureCallback) {
                    $.ajax({
                        url: '/ams/schedule/all?start='+info.startStr+'&end='+info.endStr,
                        type: 'GET',
                        success: function(data) {
                            console.log(data.data);
                            for(var i=0;i<data.data.length;i++){
                                    if(data.data[i].start == null){
                                        delete data.data[i].start;
                                        delete data.data[i].end;
                                        if(data.data[i].endTime==null){
                                            data.data[i].endRecur += 'T24:00:00'; 
                                        }
                                    }else{
                                        delete data.data[i].startRecur;
                                        delete data.data[i].endRecur;
                                        delete data.data[i].startTime;
                                        delete data.data[i].endTime;
                                        delete data.data[i].groupId;
                                        delete data.data[i].daysOfWeek;
                                        if(data.data[i].end.length < 12){
                                            data.data[i].end += 'T24:00:00'; 
                                        }
                                    }
                            } 
                            console.log(data.data);
                            successCallback(data.data);
                        }
                    });
                }
        }]
        

        });
        calendar.render();
    }
    //페이지 로딩시 월간일정 init
    calendar_init('dayGridMonth');



    
});