<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Instance slayer</title>
  </head>

  <body>
      <h1>Instances</h1>

      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Time Created</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="instance" items="${instances}">
            <tr>
              <td><c:out value="${instance.name}"/></td>
              <td><c:out value="${instance.created}"/></td>
              <td><c:out value="${instance.status}"/></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

  </body>
</html>
