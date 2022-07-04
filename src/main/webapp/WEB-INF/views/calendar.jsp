<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here1</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/dist/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css' rel='stylesheet'>
<link href='${pageContext.request.contextPath}/fullcalendar/lib/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath}/fullcalendar/lib/main.js'></script>
</head>
   <script>

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
        	  themeSystem: 'bootstrap5',
        	  initialView: 'dayGridMonth',
              eventColor: 'green',
              locale: 'kr',
              dayMaxEvents: true,
        	  events: [
        		  {
        	          title: 'ams 회의',
        	          start: '2022-07-12',
        	          end: '2022-07-13'

        	  },
    		  {
    	          title: '농땡이',
    	          start: '2022-07-12T10:30:00',
    	          end: '2022-07-13T12:30:00',
    	          color: 'purple'

    	  },    		  {
	          title: '농땡이',
	          start: '2022-07-12T10:30:00',
	          end: '2022-07-13T12:30:00',
	          color: 'purple'

	  },    		  {
          title: '농땡이',
          start: '2022-07-12T10:30:00',
          end: '2022-07-13T12:30:00',
          color: 'purple'

  },    		  {
      title: '농땡이',
      start: '2022-07-12T10:30:00',
      end: '2022-07-13T12:30:00',
      color: 'purple'

},
        	  ]
        });
        calendar.render();
        
        
      });
      $(function(){
    	  
      });
    </script>
<body>
<div class="container">
    <div id='calendar'></div>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>