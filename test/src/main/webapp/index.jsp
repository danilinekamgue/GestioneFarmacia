<html>
<%@include file="../bootstrap.jsp" %>

<script type="text/javascript" src="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/script/add-carello.js"></script>

<body>
<%@ page import="java.util.*,model.*"  %>

<div class="pos-f-t">
  <nav class="py-3 navbar-success bg-success">
    <h5 class="text-center b">Benvenuti alla FARMACIA DANIELLA</h5>
  </nav>
</div>




<div class="container-fluid mt-5">
    <div class="row">
        <div class="col-6">
            <h5 class="text-center my-4">LISTA DEI FARMACI DISPONIBILE</h5>
            <table class="table table-bordered">
                <thead>
                  <tr>
                    <th width="30%">Nome</th>
                    <th width="55%">Descrizione</th>
                    <th width="15%">Prezzo(Euro)</th>
                  </tr>
                </thead>
              <%
                  ArrayList<Farmaco> formaci=(ArrayList<Farmaco>) request.getAttribute("farmaci");
                  if(formaci != null){
                    for (Farmaco post: formaci) {
              %>
                <tr>
                  <td><%=post.getNome()%></td>
                  <td><%=post.getDescrizione()%></td>
                  <td><%=post.getPrezzo()%></td>
                </tr>
              <% }} %>
                  <%
                    if(formaci == null){
                  %>
                     <tr><td align="center" colspan="4"> Non ci sono medici disponibili ! </td></tr>
                  <% } %>
            </table>
        </div>

        <div class="col-6">
            <div class="container-fluid">
                <div class="row d-flex justify-content-center">
                    <div class="col-6">
                        <h5 class="text-center p-4"> <strong>LOGIN</strong></h5>
                          <%
                            if(session.getAttribute("errorMessage") != null){
                          %>
                             <p class="text-danger text-center"> <%= session.getAttribute("errorMessage") %> </p>
                             <% session.setAttribute("errorMessage", null); %>
                          <% } %>
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
    </div>
</div>

</body>
</html>
