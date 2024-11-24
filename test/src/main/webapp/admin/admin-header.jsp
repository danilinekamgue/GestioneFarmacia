
<nav class="navbar navbar-expand-lg  navbar-success bg-primary">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <form action="admin-farmaci" method="GET" class="form-inline my-2 my-lg-0">
                      <button class="btn px-5 btn-dark" type="submit" >
                                 FARMACI
                      </button>
                </form>
            </li>
            <li class="nav-item">
                <form action="admin-user" method="GET" class="form-inline my-2 my-lg-0">
                    <button class="btn mx-4 px-5 btn-dark" type="submit" >
                        CLIENTI
                    </button>
                </form>
            </li>
            <li class="nav-item">
                <form action="ordini-cliente" method="GET" class="form-inline my-2 my-lg-0">
                    <button class="btn mx-4 px-5 btn-dark" type="submit" >
                        ORDINI
                    </button>
                </form>
            </li>
        </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <h5 class="">Benvenuto <%= session.getAttribute("email")  %> ! </h5>
                </li>
            </ul>

            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <form action="do.logout" method="POST" class="form-inline my-2 my-lg-0">
                        <button class="btn btn-danger my-2 my-sm-0" type="submit" >
                         <i class="fas fa-sign-out-alt"></i> Logout
                        </button>
                    </form>
                </li>
            </ul>
    </div>
</nav>


    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">PharmaDaniella</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <!-- Cambia l'ordine dei link -->
                <li class="nav-item">
                    <a class="nav-link" href="/test/farmaci"></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/test/clienti">Clienti</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/test/ordini">Prenotazioni</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <form action="logout" method="post" style="display: inline;">
                        <button type="submit" class="btn btn-outline-light btn-sm">Logout</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>