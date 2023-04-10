
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form</title>
</head>
<body>
	<div align="center">
		<h2>Hero</h2>
		<form:form action="register" method="post" modelAttribute="user">
			<div>
			<form:label path="name">Full name:</form:label>
			<form:input path="name" />
			<form:errors path="name" cssClass="error" />
			</div>

			<div>
			<form:label path="health">Health:</form:label>
			<form:input path="health" />
			<form:errors path="health" cssClass="error" />
			</div>

			<div>
			<form:label path="damage">Damage:</form:label>
			<form:password path="damage"/>
			<form:errors path="damage" cssClass="error" />
			</div>


			<div>
			<form:label path="resistance">Resistance:</form:label>
			<form:input path="resistance" type="date"/>
			<form:errors path="resistance" cssClass="error" />
			</div>

		</form:form>
	</div>
</body>
</html>