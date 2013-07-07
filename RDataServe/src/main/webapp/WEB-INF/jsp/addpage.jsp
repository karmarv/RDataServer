<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NEW FILE</title>
</head>
<body>

<h1>Create New File</h1>

<c:url var="saveUrl" value="/ss/main/files/add" />
<form:form modelAttribute="fileAttribute" method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="fileId">File ID:</form:label></td>
			<td><form:input path="fileId"/></td>
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
			<td><form:label path="fileSizeInKB">File Size:</form:label></td>
			<td><form:input path="fileSizeInKB"/></td>
		</tr>
		<tr>
			<td><form:label path="fileExtensionType">Extension Type:</form:label></td>
			<td><form:input path="fileExtensionType"/></td>
		</tr>
	</table>
	
	<input type="submit" value="Save" />
</form:form>

</body>
</html>