<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>
    
    <jsp:attribute name="footer">
      <%@ include file="WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
        <p>Hi I'm the heart of the message</p>
    </jsp:body>
</t:genericpage>