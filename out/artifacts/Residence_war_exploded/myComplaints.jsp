<%@ page import="process.Complaint" %>
<%@ page import="process.ComplaintManager" %>
<%@ page import="java.util.List" %>
<!--
User: paras
-->
<%
    session.setAttribute("userId", "parasb");

    ComplaintManager userManager = new ComplaintManager();
    String userId = session.getAttribute("userId").toString();
    List<Complaint> allComplaints = userManager.getUserComplaints(userId);
    int complaintsSize = allComplaints.size();

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="msapplication-tap-highlight" content="no">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta name="theme-color" content="#c4122f"/>

    <title>Residence Life</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/AshokaFavicon.png">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css" media="screen,projection"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <style>

        .nav-wrapper {
            background-color: #c4122f;
        }

        @media only screen and (max-width: 990px) {
            html, body {
                background-color: #eeeeee;
            }
        }

        @media only screen and (min-width: 992px) {
            html, body {
                background-image: url("./images/White.jpg");
                background-repeat: no-repeat;
                background-size: cover;
                background-attachment: fixed;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
            }

        }

        .modal {
            width: 80%;
            min-height: 80% !important;
        }

    </style>
</head>

<body>
<header>
    <div class="navbar-fixed">
        <nav>
            <div class="nav-wrapper">
                <a href="#!" class="brand-logo"> &nbsp; RESIDENCE LIFE</a>
                <a href="#" data-activates="mobile-menu" class="button-collapse"><img src="./images/menu.svg"
                                                                                      style="vertical-align:middle;"></a>
                <ul class="right hide-on-med-and-down">
                    <li><a href="userMenu.html"> Home</a></li>
                    <li><a href=""> Feedback</a></li>
                    <li><a href="collapsible.html">Logout</a></li>
                </ul>
                <ul class="side-nav" id="mobile-menu">
                    <li><a href="userMenu.html"> Home</a></li>
                    <li><a href=""> Feedback</a></li>
                    <li><a href="collapsible.html">Logout</a></li>

                </ul>
            </div>
        </nav>
    </div>
</header>
<main>
    <div class="container">
        <div class="row">
            <div class="col l12 m12 s12">
                <ul class="collection with-header">
                    <li class="collection-header"><h4>My Complaints</h4></li>
                    <li class="collection-item">
                        <div>

                            <table class="responsive-table">
                                <tr>
                                    <th>Priority</th>
                                    <th>Date/time</th>
                                    <th>Issue</th>
                                    <th>Department</th>
                                    <th>Type</th>
                                    <th>Status</th>
                                </tr>

                                <%
                                    for (int i = 0; i < complaintsSize; i++) {
                                        Complaint singleComplaint = allComplaints.get(i);
                                %>
                                <tr>
                                    <td><i class="material-icons" style="color: #c4122f;">label</i></td>
                                    <td><%=singleComplaint.getDateOfComplaint()%>
                                    </td>
                                    <td><%=singleComplaint.getIssue()%>
                                    </td>
                                    <td><%=singleComplaint.getDepartment()%>
                                    </td>
                                    <td><%=singleComplaint.getType()%>
                                    </td>
                                    <td><%=singleComplaint.getStatus()%>
                                    </td>
                                </tr>
                                <%}%>

                            </table>

                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</main>

</body>
<script type="text/javascript" src="materialize/js/jquery.js"></script>
<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
<script>
    $(".button-collapse").sideNav();
</script>
</html>