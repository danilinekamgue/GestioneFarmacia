<html>
<body>

<div >

</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-8">
            <table class="table">
                <thead>
                  <tr>
                    <th>Nome</th>
                    <th>Descrizione</th>
                    <th>Prezzo</th>
                    <th>Azione</th>
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
                  <td> <button > + add </button></td>
                </tr>
              <% }} %>
                  <%
                    if(formaci == null){
                  %>
                     <tr><td align="center" colspan="3"> Non ci sono medici disponibili ! </td></tr>
                  <% } %>
            </table>
        </div>

        <div class="col-4">
            <div class="container-fluid bg-success">
                <h5>ORDINE</h5>
            </div>
        </div>
    </div>
</div>

</body>
</html>
