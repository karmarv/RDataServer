<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EDIT</title>
</head>
<body>

<h1>Edit File</h1>

<c:url var="saveUrl" value="/ss/main/files/edit?fileId=${fileAttribute.fileId}" />
<form:form modelAttribute="fileAttribute" method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="fileId">Id:</form:label></td>
			<td><form:input path="fileId" disabled="true"/></td>
		</tr>
		<tr>
			<td><form:label path="fileName">File Name:</form:label></td>
			<td><form:input path="fileName"/></td>
		</tr>
		<tr>
			<td><form:label path="filePath">File Path:</form:label></td>
			<td><form:input path="filePath"/></td>
		</tr>		
		<tr>
			<td><form:label path="fileSize">File Size:</form:label></td>
			<td><form:input path="fileSize"/></td>
		</tr>
		<tr>
			<td><form:label path="fileType">Extension Type:</form:label></td>
			<td><form:input path="fileType"/></td>
		</tr>
	</table>
	
	<input type="submit" value="Save" />
</form:form>

</body>
</html>