<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CitiWallet</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"  crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" 
    crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" 
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" 
    crossorigin="anonymous"></script>
    <style>
h1 {
  font-size: 50px;
}
</style>
    
</head>

<body>
    <nav class="navbar navbar-expand-sm bg-default navbar-default">
        <h1><a class="nav-brand" style="color:black;">Citi</a></h1><h1><a class="nav-brand" style="color:red;">Pe</a></h1>
       <ul class="navbar-nav">
            <li class="nav-item active">
              <a class="nav-link" href="Newpayment.jsp">New Payment</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="CheckBalance">Balance Enquiry</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="TopupWallet.jsp">Topup Wallet</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="UserProfile.jsp">Payment Activity</a>
            </li>
          </ul> 
        
           <ul class="navbar-nav ml-auto">
           <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">User Profile<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="changePin.jsp">Change Pin</a></li>
           <li><a href="LogoutServlet">Logout</a></li>
            </li>
      </nav>
</body>
</html>