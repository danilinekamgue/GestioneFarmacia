
<%@include file="bootstrap.jsp" %>
<div>
    <h3 class=" mt-5 pt-5 text-center text-danger"> <%= session.getAttribute("error") %> </h3>



</div>
<div class=" d-flex justify-content-center mt-5">
<% String url = (String)session.getAttribute("url"); %>

 <a href="<%= url %>" class="btn btn-primary">TORNA ALLA HOME</a>

</div>