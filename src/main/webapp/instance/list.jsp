<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Instance slayer</title>

    <!-- Bootstrap core CSS -->
    <link href="/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="/static/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet"> -->

    <!-- Custom styles for this template -->
    <!-- <link href="theme.css" rel="stylesheet"> -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="container theme-showcase" role="main">

      <h1>Instances</h1>

      <table class="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Name</th>
            <th>Image</th>
            <th>Flavor</th>
            <th>Time Created</th>
            <th>Status</th>
            <th>Notes</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="instance" items="${instances}">
            <tr>
              <td><c:out value="${instance.name}"/></td>
              <td><c:out value="${instance.imageName}"/></td>
              <td><a tabindex="0" title="${instance.flavor.name}" data-toggle="popover" data-trigger="focus" data-content="vcpus: ${instance.flavor.vcpus} <br/> ram: ${instance.flavor.ram} MiB <br/> swap: ${instance.flavor.swap} MiB <br/> disk: ${instance.flavor.disk} GiB" data-html="true"><c:out value="${instance.flavor.name}"/></a></td>
              <td><c:out value="${instance.created}"/></td>
              <td><c:out value="${instance.status}"/></td>
              <td>
                <c:out value="${instance.notes}"/>
                <a tabindex="0" title="${instance.name} notes" class="edit pull-right" data-toggle="edit" data-placement="bottom" data-html="true" data-content="
                  <form action='/instances/${instance.id}' method='post' class='form-inline' accept-charset='UTF-8'>
                    <textarea maxlength='200' style='resize:none' class='form-control' name='notes' rows='3'><c:out value='${instance.notes}'/></textarea>
                    <button id='save' type='submit' class='btn button-small btn-primary glyphicon glyphicon-ok'></button>
                  </form>
                  "><span class="glyphicon glyphicon-pencil pull-right"></span>
                </a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/static/jquery/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="/static/jquery/jquery.min.js"><\/script>')</script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
    <script>
        $(document).ready(function(){
            $('[data-toggle="popover"]').popover();
            $('[data-toggle="edit"]').popover().on('shown.bs.popover', function() {
                $('textarea[name="notes"]').focus();
            });
            $('[data-toggle="edit"]').on('click', function (e) {
                $('[data-toggle="edit"]').not(this).popover('hide');
            });
        });
    </script>
  </body>
</html>
