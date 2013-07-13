<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	

<base href="${pageContext.request.contextPath}"></base>

<script type="text/javascript">

	$(document).ready(function() {
		$('#table_id').dataTable({
			"sPaginationType" : "full_numbers",
			"bJQueryUI": true
		});
	});

	/* Global var for counter */
	var giCount = 1;
	function fnClickAddRow() {
		$('#table_id').dataTable().fnAddData(
				[ +".1", giCount + ".2", giCount + ".3", giCount + ".4" ]);

		giCount++;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FILES</title>
</head>
<body>
	<h1>Files</h1>
	<a href="javascript:void(0);" onclick="fnClickAddRow();">Click to
		add a new row</a>

	<div id="container">
		<table id="table_id" class="display"
			style="border: 1px solid; width: 500px; text-align: center">
			<c:url var="addUrl" value="/ss/main/files/add" />
			<thead>
				<tr>
					<th>File ID</th>
					<th>File Name</th>
					<th>Version</th>
					<th>File Size</th>
					<th>Type</th>
					<th><a href="${addUrl}">Add</a></th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${files}" var="file">
					<c:url var="editUrl"
						value="/ss/main/files/edit?fileId=${file.fileId}" />
					<c:url var="deleteUrl"
						value="/ss/main/files/delete?fileId=${file.fileId}" />
					<tr>
						<td><c:out value="${file.fileId}" /></td>
						<td><c:out value="${file.fileName}" /></td>
						<td><c:out value="${file.fileVersionId}" /></td>
						<td><c:out value="${file.fileSize}" /></td>
						<td><c:out value="${file.fileType}" /></td>
						<td><a href="${editUrl}">Edit</a></td>
						<td><a href="${deleteUrl}">Delete</a></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>