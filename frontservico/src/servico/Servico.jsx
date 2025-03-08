import React, {useEffect, useState} from "react";
import './Servico.css';
import axios from 'axios';

function Servico() {

    const [servico, setServico] = useState({nomeCliente:'', dataInicio:'', dataTermino:'', descricaoServico:'', valorServico:'', valorPago:'', dataPagamento:''});
    const [servicos, setServicos] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/servico/").then(result=>{
            setServicos(result.data);
        });
    },[]);

    function handleChange(event){
        setServico({...servico,[event.target.name]:event.target.value});
    }

    function HandleSubmit(event){
        event.preventDefault();
        if (servico.id){
            axios.post("http://localhost:8080/api/servico/", servico).then(result=>{
                console.log(result);
            });
        } else {
            axios.put("http://localhost:8080/api/servico/", servico).then(result=>{
                console.log(result);
            });
        }
    }

    return (
        <div className="container">
            <h1> Cadastro de Seriviços </h1>
            <form onSubmit={HandleSubmit}>
                <div className="col-6">
                    <div>
                        <label className="form-label"> Nome do Cliente </label>
                        <input onChange={handleChange} value={servico.nomeCliente} name="nomeCliente" type="text"
                               className="form-control"/>
                    </div>
                    <div>
                        <label className="form-label"> Data de Inicio </label>
                        <input onChange={handleChange} value={servico.dataInicio} name="dataInicio" type="date"
                               className="form-control"/>
                    </div>
                    <div>
                        <label className="form-label"> Data de Termino </label>
                        <input onChange={handleChange} value={servico.dataTermino} name="dataTermino" type="date"
                               className="form-control"/>
                    </div>
                    <div>
                        <label className="form-label"> Descrição do Serviço </label>
                        <input onChange={handleChange} value={servico.descricaoServico} name="descricaoServico"
                               type="text" className="form-control"/>
                    </div>
                    <div>
                        <label className="form-label"> Valor do Serviço </label>
                        <input onChange={handleChange} value={servico.valorServico} name="valorServico" type="number"
                               className="form-control"/>
                    </div>
                    <div>
                        <label className="form-label"> Valor Pago </label>
                        <input onChange={handleChange} value={servico.valorPago} name="valorPago" type="number"
                               className="form-control"/>
                    </div>
                    <div>
                        <label className="form-label"> Data do Pagamento </label>
                        <input onChange={handleChange} value={servico.dataPagamento} name="dataPagamento" type="date"
                               className="form-control"/>
                    </div>
                    <br/>
                    <input type="submit" value="Cadastrar" className="btn btn-success"></input>

                </div>
                <hr></hr>
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
                    {servicos.map(serv => (
                            <tr>
                                <td>{serv.nomeCliente}</td>
                                <td>{serv.descricaoServico}</td>
                                <td>{serv.valorServico}</td>
                                <td>{serv.status}</td>
                                <td>
                                    {serv.status!="cancelado" &&
                                    <button onClick={()=>setServico(serv)} className="btn btn-primary">Alterar</button>
                                    }&nbsp;&nbsp;
                                    {serv.status!='cancelado' &&
                                    <button className="btn btn-danger">Excluir</button>
                                    }&nbsp;&nbsp;
                                    <button className="btn btn-warning">Cancelar</button>
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
