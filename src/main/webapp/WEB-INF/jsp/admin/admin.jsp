<%@ page contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"   %>
<html ng-app="myApp">
<head >
	<title>{{headingTitle}}</title>
</head>

<t:genericpage>
<jsp:attribute name="header">
<%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</jsp:attribute>

<jsp:attribute name="footer">
<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
</jsp:attribute>
<jsp:body>
<div id="admin" class="row"  >
	<div >
    <div ng-controller="UsersController">
        <div class="block">
            <select ng-model="selection">  
                <option value="area1" >Taipei</option>  
                <option value="area2">Taoyuan</option>  
            </select>
            <div ng-show="selection=='area1'">This is Taipei</div>
            <div ng-show="selection=='area2'">This is Taoyuan</div>
        </div><!--block-->
        <div class="block">
            <input type="checkbox" ng-model="showDom"> 
            <span ng-show="showDom">Show me</span>
        </div>
        <div class="block">
             <input type="checkbox" ng-model="hideDom"> 
            <span ng-hide="hideDom">Hide me</span>            
        </div>
        <div class="block"><div>
            <button class="" ng-click="showCon('con1')">Button1</button>   
            <button class="" ng-click="showCon('con2')">Button2</button> 
            <button class="" ng-click="showCon('con3')">Button3</button> 
            </div>
            <div ng-show="clickOn=='con1'">con1</div>
            <div ng-show="clickOn=='con2'">con2</div>
            <div ng-show="clickOn=='con3'">con3</div>
        </div>
    </div>
</div>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.js"></script>
<script type="text/javascript" src="webjars/angularjs/1.3.8/angular.min.js"></script>
<script type="text/javascript" src="webjars/angularjs/1.3.8/angular-resource.min.js"></script>

</jsp:body>
</t:genericpage>
</html>
