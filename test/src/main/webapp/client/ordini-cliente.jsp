<html>

<%@include file="../bootstrap.jsp" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="/test/script/add-carello.js"></script>

<%@ page import="java.util.*,model.*"  %>
<body>
<jsp:include page="header-client.jsp" />

<h1> ORDINE </h1>

              <%
                  ArrayList<OrdineModel> ordini=(ArrayList<OrdineModel>) session.getAttribute("ordini");
                  if(ordini != null){
                    for (OrdineModel ordine: ordini) {
              %>
                  <div>Ordine : <%=ordine.getNumero()%>   </div>

                    <%
                       for (Farmaco farmaco: ordine.getFarmaci()) {
                    %>
                    <div>Ordine : <%=farmaco.getNome()%>   </div>

              <% }}} %>

              <%
                    if(ordini == null){
                  %>
                    Non ci sono ordini
                  <% } %>


</body>
</html>
