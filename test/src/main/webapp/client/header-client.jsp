


<nav class="navbar navbar-expand-lg  navbar-success bg-success">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <form action="cliente-farmaci" method="GET" class="form-inline my-2 my-lg-0">
                      <button class="btn px-5 btn-dark" type="submit" >
                                 FARMACI
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

