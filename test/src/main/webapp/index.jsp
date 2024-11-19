<html>
<%@include file="../bootstrap.jsp" %>
<script type="text/javascript" src="/test/script/add-carello.js"></script>
<body>
<%@ page import="java.util.*,model.*"  %>
<h2 class="bg-warning">FARMACIA DANIELLA</h2>


<div class="container-fluid">
    <div class="row">
        <div class="col-9">
            <h5 class="text-center my-4">LISTA DEI FARMACI DISPONIBILE</h5>
            <table class="table">
                <thead>
                  <tr>
                    <th width="30%">Nome</th>
                    <th width="60%">Descrizione</th>
                    <th width="10%">Prezzo</th>
                  </tr>
                </thead>
              <%
                  ArrayList<FarmacoModel> formaci=(ArrayList<FarmacoModel>) request.getAttribute("farmaci");
                  if(formaci != null){
                    for (FarmacoModel post: formaci) {
              %>
                <tr>
                  <td><%=post.getNome()%></td>
                  <td><%=post.getId()%></td>
                  <td><%=post.getId()%></td>
                </tr>
              <% }} %>
                  <%
                    if(formaci == null){
                  %>
                     <tr><td align="center" colspan="4"> Non ci sono medici disponibili ! </td></tr>
                  <% } %>
            </table>
        </div>

        <div class="col-3">
            <div class="container-fluid">
                <h5 class="text-center p-4">LOGIN</h5>

                <form action="login" method="POST">
                  <!-- Email input -->
                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="email" name="email" id="form2Example1" class="form-control" />
                    <label class="form-label" for="form2Example1">Email address</label>
                  </div>

                  <!-- Password input -->
                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="password" name="password" id="form2Example2" class="form-control" />
                    <label class="form-label" for="form2Example2">Password</label>
                  </div>

                  <!-- Submit button -->
                  <button  type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4">Sign in</button>

                  <div>
                    <p class="text-center"> You forgot your password or you do not have an account? Contact the admin </p>
                  </div>

                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
