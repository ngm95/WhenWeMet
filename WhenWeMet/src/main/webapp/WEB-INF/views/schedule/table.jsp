<%@page import="com.spring.project.dto.CalendarDTO"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>
</head>
<%
LinkedList<CalendarDTO> list = (LinkedList<CalendarDTO>)session.getAttribute("JSONSchedule"); 
CalendarDTO arr[] = new CalendarDTO[list.size()];
arr = list.toArray(new CalendarDTO[list.size()]);
%>
<body class="main-pages">
	<div class="container" style="height: 100%">
		<%@ include file="/WEB-INF/views/includes/03_header.jsp"%>
		
		<div id='calendar'></div>
		<div id="test"></div>
	</div>
	
	<input type="hidden" id="mid" value="${meeting.mid }">
	<input type="hidden" id="JSONSchedule" value="${JSONSchedule }">
</body>
<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
<link href='${pageContext.request.contextPath }/resources/fullcalendar/core/main.css' rel='stylesheet' />
<link href='/resources/fullcalendar/daygrid/main.css' rel='stylesheet' />
<link href='/resources/fullcalendar/timegrid/main.css' rel='stylesheet' />
<script src='/resources/fullcalendar/core/main.js'></script>
<script src='/resources/fullcalendar/interaction/main.js'></script>
<script src='/resources/fullcalendar/daygrid/main.js'></script>
<script src='/resources/fullcalendar/timegrid/main.js'></script>
<script src='/resources/moment/min/moment.min.js'></script>
<script>
var json = String($('#JSONSchedule').val());
var titleArray = new Array();
var startArray = new Array();
var endArray = new Array();
<c:forEach items="${JSONSchedule}" var="item">
	titleArray.push("${item.title}");
	startArray.push("${item.start}");
	endArray.push("${item.end}");
</c:forEach>

document.addEventListener('DOMContentLoaded', function() {
	
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: ['dayGrid', 'timeGrid' ],
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek'
      },
      defaultDate: '2019-10-21',
      navLinks: true, // can click day/week names to navigate views
      selectable: false,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('Event Title:');
        if (title) {
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })
        }
        calendar.unselect()
      },
      editable: false,
      eventLimit: true, // allow "more" link when too many events
      event:[
    	 
      ]
    });
 	for (var i = 0; i < titleArray.length; i++) {
 		var title = titleArray[i];
 		var startDate = startArray[i];
 		var endDate = endArray[i];
 		calendar.addEvent({'title':title, 'start':startDate, 'end':endDate});
 	}
    
    
    calendar.render();
  });
</script>
<style>

  body {
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

</style>

</html>