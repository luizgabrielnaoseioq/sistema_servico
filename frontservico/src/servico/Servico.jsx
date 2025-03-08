import React, { useEffect, useState } from "react";
import './Servico.css';
import axios from 'axios';

function Servico() {
    const [servico, setServico] = useState({
        id: undefined, // Adicionei o id como undefined inicialmente
        nomeCliente: "",
        dataInicio: "",
        dataTermino: "",
        descricaoServico: "",
        valorServico: "",
        valorPago: "",
        dataPagamento: "",
    });
    const [servicos, setServicos] = useState([]);

    useEffect(() => {
        buscarTodos();
    }, []);

    // Função para tratar mudanças nos campos do formulário
    function handleChange(event) {
        setServico({ ...servico, [event.target.name]: event.target.value });
    }

    // Função para lidar com o envio do formulário
    function HandleSubmit(event) {
        event.preventDefault();

        // Se não houver id, é um cadastro novo
        if (servico.id === undefined) {
            axios.post("http://localhost:8080/api/servico/", servico)
                .then(result => {
                    console.log(result);
                    buscarTodos(); // Atualiza a lista de serviços após o cadastro
                })
                .catch(error => {
                    console.error("Erro ao cadastrar serviço:", error);
                });
        } else {
            // Se já existir um id, é uma atualização
            axios.put(`http://localhost:8080/api/servico/${servico.id}`, servico)
                .then(result => {
                    console.log(result);
                    buscarTodos(); // Atualiza a lista de serviços após a atualização
                })
                .catch(error => {
                    console.error("Erro ao atualizar serviço:", error);
                });
        }

        limpar();
    }

    // Função para limpar os campos do formulário
    function limpar() {
        setServico({
            id: undefined, // Reseta o id ao limpar o formulário
            nomeCliente: "",
            dataInicio: "",
            dataTermino: "",
            descricaoServico: "",
            valorServico: "",
            valorPago: "",
            dataPagamento: "",
        });
    }

    // Função para excluir um serviço
    function excluir(id) {
        axios.delete(`http://localhost:8080/api/servico/${id}`)
            .then(() => {
                setServicos(servicos.filter(serv => serv.id !== id));
            })
            .catch(error => {
                console.error("Erro ao excluir serviço:", error);
            });
    }

    // Função para cancelar um serviço
    function cancelar(id) {
        axios.put(`http://localhost:8080/api/servico/${id}`, { status: "cancelado" })
            .then(() => {
                setServicos(servicos.map(serv =>
                    serv.id === id ? { ...serv, status: "cancelado" } : serv
                ));
            })
            .catch(error => console.error("Erro ao cancelar serviço:", error));
    }

    // Função para buscar todos os serviços
    function buscarTodos() {
        axios.get("http://localhost:8080/api/servico/")
            .then(result => {
                console.log("Dados recebidos:", result.data);
                setServicos(result.data.filter(serv => serv.nomeCliente)); // Filtra itens sem nomeCliente
            })
            .catch(error => {
                console.error("Erro ao buscar serviços:", error);
            });
    }

    // Função para buscar serviços com pagamento pendente
    function buscarPagamentoPendente() {
        axios.get("http://localhost:8080/api/servico/pagamentoPendente")
            .then(result => {
                console.log("Dados recebidos:", result.data);
                setServicos(result.data.filter(serv => serv.nomeCliente)); // Filtra itens sem nomeCliente
            })
            .catch(error => {
                console.error("Erro ao buscar serviços com pagamento pendente:", error);
            });
    }

    // Função para buscar serviços cancelados
    function buscarPagamentoCancelado() {
        axios.get("http://localhost:8080/api/servico/cancelados")
            .then(result => {
                console.log("Dados recebidos:", result.data);
                setServicos(result.data.filter(serv => serv.nomeCliente)); // Filtra itens sem nomeCliente
            })
            .catch(error => {
                console.error("Erro ao buscar serviços cancelados:", error);
            });
    }

    // Função para preencher os campos do formulário ao clicar em "Alterar"
    function editarServico(serv) {
        setServico(serv); // Preenche os campos com os dados do serviço
    }

    return (
        <div className="container">
            <h1> Cadastro de Serviços </h1>
            <form onSubmit={HandleSubmit}>
                <div className="col-6">
                    <div>
                        <label className="form-label"> Nome do Cliente </label>
                        <input
                            onChange={handleChange}
                            value={servico.nomeCliente || ''}
                            name="nomeCliente"
                            type="text"
                            className="form-control"
                        />
                    </div>
                    <div>
                        <label className="form-label"> Data de Inicio </label>
                        <input
                            onChange={handleChange}
                            value={servico.dataInicio || ''}
                            name="dataInicio"
                            type="date"
                            className="form-control"
                        />
                    </div>
                    <div>
                        <label className="form-label"> Data de Termino </label>
                        <input
                            onChange={handleChange}
                            value={servico.dataTermino || ''}
                            name="dataTermino"
                            type="date"
                            className="form-control"
                        />
                    </div>
                    <div>
                        <label className="form-label"> Descrição do Serviço </label>
                        <input
                            onChange={handleChange}
                            value={servico.descricaoServico || ''}
                            name="descricaoServico"
                            type="text"
                            className="form-control"
                        />
                    </div>
                    <div>
                        <label className="form-label"> Valor do Serviço </label>
                        <input
                            onChange={handleChange}
                            value={servico.valorServico || ''}
                            name="valorServico"
                            type="number"
                            className="form-control"
                        />
                    </div>
                    <div>
                        <label className="form-label"> Valor Pago </label>
                        <input
                            onChange={handleChange}
                            value={servico.valorPago || ''}
                            name="valorPago"
                            type="number"
                            className="form-control"
                        />
                    </div>
                    <div>
                        <label className="form-label"> Data do Pagamento </label>
                        <input
                            onChange={handleChange}
                            value={servico.dataPagamento || ''}
                            name="dataPagamento"
                            type="date"
                            className="form-control"
                        />
                    </div>
                    <br />
                    <input
                        type="submit"
                        value={servico.id ? "Atualizar" : "Cadastrar"}
                        className="btn btn-success"
                    />
                </div>
                <hr />
                <hr />

                <button onClick={buscarTodos} type="button" className="btn btn-primary">Listar Todos</button>&nbsp;&nbsp;
                <button onClick={buscarPagamentoPendente} type="button" className="btn btn-secondary">Serviços com Pagamento Pendente</button>&nbsp;&nbsp;
                <button onClick={buscarPagamentoCancelado} type="button" className="btn btn-success">Serviços Cancelados</button>

                <table className="table">
                    <thead>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Descrição</th>
                        <th scope="col">Valor</th>
                        <th scope="col">Status</th>
                        <th scope="col">Opções</th>
                    </tr>
                    </thead>
                    <tbody>
                    {servicos.map((serv) => (
                        <tr key={serv.id}>
                            <td>{serv.nomeCliente}</td>
                            <td>{serv.descricaoServico}</td>
                            <td>{serv.valorServico}</td>
                            <td>{serv.status}</td>
                            <td>
                                {serv.status !== "cancelado" && (
                                    <button
                                        onClick={() => editarServico(serv)}
                                        className="btn btn-primary"
                                    >
                                        Alterar
                                    </button>
                                )}
                                &nbsp;&nbsp;
                                {serv.status !== 'cancelado' && (
                                    <button
                                        onClick={() => excluir(serv.id)}
                                        className="btn btn-danger"
                                    >
                                        Excluir
                                    </button>
                                )}
                                &nbsp;&nbsp;
                                <button
                                    onClick={() => cancelar(serv.id)}
                                    className="btn btn-warning"
                                >
                                    Cancelar
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </form>
        </div>
    );
}

export default Servico;
