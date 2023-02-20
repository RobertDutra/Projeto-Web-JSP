<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <style>
    h1{
    text-a
    }
    </style>
</head>
<body class="container mt-5">
    <h1 class="text-success position-absolute start-50 translate-middle shadow-lg p-3 mb-5 bg-body rounded">Cadastro de Usuário</h1>
    <button class="btn btn-success mt-5" data-bs-toggle="modal" data-bs-target="#createModal">+ Adicionar</button>
    <table class="table mt-4 bg-success p-2 text-dark bg-opacity-25 border">
        <thead>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>Endereço</th>
                <th></th>
                <th></th>
            </tr>

        </thead>

        <tbody>
            <c:forEach var="p" items="${pessoas}" >
                <tr>
                    <td>${p.getId()}</td>
                    <td>${p.getNome()}</td>
                    <td>${p.getEndereco()}</td>
                    <td>
                        <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#updateModal" onclick="onUpdate(${p.id}, '${p.nome}', '${p.endereco}')">Editar</button>

                        <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="onDelete(${p.id}, '${p.nome}', '${p.endereco}')">Deletar</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Modal: create-->
    <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Adicionar Pessoa</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/senai-web/">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome </label>
                            <input type="text" class="form-control" id="nome" name="nome">
                        </div>
                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" class="form-control" id="endereco" name="endereco">
                        </div>
                        <input type="hidden" name="action" value="create"/>
                        <button type="submit" class="btn btn-outline-success">Adicionar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <!-- Modal: update-->
    <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Atualizar Pessoa</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/senai-web/">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome </label>
                            <input type="text" class="form-control" id="nome" name="nome">
                        </div>
                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" class="form-control" id="endereco" name="endereco">
                        </div>
                        <input type="hidden" name="id" value=""/>
                        <input type="hidden" name="action" value="update"/>
                        <button type="submit" class="btn btn-outline-success">Atualizar</button>
                        <button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal">Cancelar</button>

                    </form>
                </div>
            </div>
        </div>
    </div>


    <!-- Modal: delete-->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Deseja realmente remover essa pessoa?</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/senai-web/">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome </label>
                            <input type="text" class="form-control" id="nome" name="nome" readonly="">
                        </div>
                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" class="form-control" id="endereco" name="endereco" readonly="">
                        </div>
                        <input type="hidden" name="id" value=""/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit" class="btn btn-outline-danger">Deletar</button>
                        <button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal">Cancelar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        function onUpdate(id, nome, endereco){

            var idPerson = document.querySelector("#updateModal input[name=id]");
            var nomePerson = document.querySelector("#updateModal input[name=nome]");
            var enderecoPerson = document.querySelector("#updateModal input[name=endereco]");

            idPerson.value = id;
            nomePerson.value = nome;
            enderecoPerson.value = endereco;

        }

        function onDelete(id, nome, endereco){

            var idPerson = document.querySelector("#deleteModal input[name=id]");
            var nomePerson = document.querySelector("#deleteModal input[name=nome]");
            var enderecoPerson = document.querySelector("#deleteModal input[name=endereco]");

            idPerson.value = id;
            nomePerson.value = nome;
            enderecoPerson.value = endereco;

        }
    </script>
</body>
</html>