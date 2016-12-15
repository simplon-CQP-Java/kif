<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<head>
    <title>Historique des réservations</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    
    <jsp:body>
    <table class="table">
        <c:forEach items="${history}" var="booking">
                 <tr>
                    <td><p>${booking.user.username}</p></td>
                    <td><p>${booking.start}</p></td>
                    <td><p>${booking.end}</p></td>
                    <td><p>${booking.room.name}</p></td>
                    <td><p>${booking.computer.brand}</p></td>
                 </tr>
        </c:forEach>
    </table>    
    </jsp:body>
</t:genericpage>