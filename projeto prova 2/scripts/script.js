let carrinho = JSON.parse(localStorage.getItem('carrinho')) || [];

const adicionaAoCarrinho = (nomeProduto, precoProduto) => {
    const produto = { nome: nomeProduto, preco: precoProduto };
    carrinho.push(produto);
    atualizaContagemCarrinho();
    salvarCarrinho();
    alert(`O produto ${nomeProduto} foi adicionado ao seu carrinho.`);
}
window.adicionaAoCarrinho = adicionaAoCarrinho;

const atualizaContagemCarrinho = () => {
    document.getElementById('carrinho-contagem').textContent = carrinho.length;
}
window.atualizaContagemCarrinho = atualizaContagemCarrinho;

const salvarCarrinho = () => {
    localStorage.setItem('carrinho', JSON.stringify(carrinho));
}
window.salvarCarrinho = salvarCarrinho;

const carregaCarrinho = () => {
    carrinho = JSON.parse(localStorage.getItem('carrinho')) || [];
    atualizaContagemCarrinho();
    mostrarItensCarrinho();
}
window.carregaCarrinho = carregaCarrinho;

const mostrarItensCarrinho = () => {
    const containerCarrinho = document.getElementById('carrinho-container');
    const totalCarrinho = document.getElementById('carrinho-total');
    containerCarrinho.innerHTML = '';
    let total = 0;

    carrinho.forEach((produto, indice) => {
        const itemCarrinho = document.createElement('div');
        itemCarrinho.classList.add('carrinho__item');
        
        itemCarrinho.innerHTML = `
            <img src="./img/${produto.nome}.jpg" alt="${produto.nome}">
            <div class="carrinho__item--detalhes">
                <h3>${produto.nome}</h3>
                <p>${produto.preco.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
            </div>
            <button onclick="removerItemCarrinho(${indice})">Remover</button>
        `;

        containerCarrinho.appendChild(itemCarrinho);
        total += produto.preco;
    });

    totalCarrinho.textContent = total.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
}
window.mostrarItensCarrinho = mostrarItensCarrinho;

const removerItemCarrinho = (indice) => {
    carrinho.splice(indice, 1);
    atualizaContagemCarrinho();
    salvarCarrinho();
    mostrarItensCarrinho();
}
window.removerItemCarrinho = removerItemCarrinho;

const limpaCarrinho = () => {
    carrinho = [];
    atualizaContagemCarrinho();
    salvarCarrinho();
    mostrarItensCarrinho();
}
window.limpaCarrinho = limpaCarrinho;


//RESPOSTAS

//Etapa 02
//URL para buscar: https://viacep.com.br/ws/58400240/json/
//Método http para usar: GET
//Resposta do Reject: reject('Erro ao consultar o CEP'))
const buscarEndereco = (cep) => {
    return new Promise((resolve, reject) => {
        fetch(`https://viacep.com.br/ws/${cep}/json/`)
            .then(response => {
                if (!response.ok) throw new Error('Network response was not ok');
                return response.json();
            })
            .then(data => {
                if (data.erro) {
                    reject('Erro ao consultar o CEP');
                } else {
                    resolve(data);
                }
            })
            .catch(() => reject('Erro ao consultar o CEP'));
    });
}

const consultaCep = () => {
    const cep = document.getElementById('cep').value.replace(/\D/g, '');
    if (cep.length === 8) {
        buscarEndereco(cep)
            .then(data => {
                document.getElementById('logradouro').value = data.logradouro || '';
                document.getElementById('complemento').value = data.complemento || '';
                document.getElementById('cidade').value = data.localidade || '';
                document.getElementById('estado').value = data.uf || '';
            })
            .catch(error => alert(error));
    } else {
        alert('CEP inválido!');
    }
};
window.consultaCep = consultaCep;

//Etapa 03
const gerarTextoMarketeiro = (dadosFormulario) => {

    const container = document.querySelector('.marketeiro .container') || document.body;
    let card = document.getElementById('card-marketeiro');
    if (!card) {
        card = document.createElement('div');
        card.id = 'card-marketeiro';
        card.style.maxWidth = '800px';
        card.style.margin = '20px auto';
        card.style.padding = '20px';
        card.style.borderRadius = '8px';
        card.style.boxShadow = '0 2px 8px rgba(0,0,0,0.1)';
        card.style.backgroundColor = '#ffffff';
        card.style.fontFamily = 'Arial, sans-serif';
        container.appendChild(card);
    }

    const nome = dadosFormulario.nome || '';
    const email = dadosFormulario.email || '';
    const motivo = dadosFormulario.motivo || '';
    const cidade = dadosFormulario.cidade || '';
    const endereco = dadosFormulario.endereco || '';
    const cep = dadosFormulario.cep || '';

    card.innerHTML = `
        <h2 style="text-align:center; color:#222;">Perfil Profissional</h2>
        <p>Apresentamos ${nome}, um profissional altamente qualificado e referência no desenvolvimento avançado de software. Com uma trajetória pautada pela inovação e excelência, ${nome} tem se destacado na criação de soluções tecnológicas de alto impacto, na qual tem transformado desafios complexos em sistemas eficientes e escaláveis.</p>
        <p>Comunicável e estrategista, ${nome} pode ser contatado via e-mail em ${email}, mantendo-se sempre disponível para colaborações e projetos que demandem expertise em engenharia de software, inteligência artificial e programação web. Seu principal objetivo no momento é ${motivo}, reforçando sua busca contínua pelo aprimoramento e pela entrega de soluções robustas e inteligentes.</p>
        <p>Atualmente, ${nome} reside na dinâmica cidade de ${cidade}, no endereço ${endereco}, CEP ${cep}, onde continua sua missão de criar e arquitetar aplicações inovadoras. Seu conhecimento aprofundado em diversas linguagens, frameworks e metodologias ágeis o posiciona como um líder técnico capaz de elevar qualquer equipe ao mais alto nível de performance.</p>
        <p>Com uma visão futurista e uma abordagem precisa para o desenvolvimento de software, ${nome} segue transformando o cenário tecnológico com soluções que transcendem expectativas.</p>
    `;

}
window.gerarTextoMarketeiro = gerarTextoMarketeiro;
window.gerarTextoMarketeiro = gerarTextoMarketeiro;

//Não mexer neste método
function submeterDados(event) {

    const dadosFormulario = {
        nome: document.getElementById('nome').value,
        email: document.getElementById('email').value,
        motivo: document.getElementById('motivo').value,
        cep: document.getElementById('cep').value,
        endereco: document.getElementById('logradouro').value,
        endereco: document.getElementById('complemento').value,
        cidade: document.getElementById('cidade').value,
        estado: document.getElementById('estado').value
    };

    gerarTextoMarketeiro(dadosFormulario);
};


//Etapa 04
//URL para buscar: https://fakestoreapi.com/products
//Método http para usar: GET
//Resposta do Reject: reject('Erro ao consultar os Produtos'))
const consultarProdutosExternos = () => {

    return new Promise((resolve, reject) => {
        fetch('https://fakestoreapi.com/products')
            .then(response => {
                if (!response.ok) throw new Error('Network response was not ok');
                return response.json();
            })
            .then(data => {
                // map first 6 products into the shape we need
                const produtos = data.slice(0, 6).map(p => ({
                    nome: p.title,
                    preco: p.price,
                    imagem: p.image
                }));
                resolve(produtos);
            })
            .catch(() => reject('Erro ao consultar os Produtos'));
    })
}

const alterarValoresTabela = () => {
    consultarProdutosExternos().then(data => {
        const tabela = document.getElementById("tabelaProdutos").getElementsByTagName('tbody')[0];
        for (let i = 0; i < 6; i++) {
            const produto = data[i];
            const row = tabela.rows[i];
            if (!produto || !row) continue;
            // Nome
            row.cells[0].innerText = produto.nome;
            // Preço formatado
            row.cells[1].innerText = produto.preco.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
            // Imagem
            row.cells[2].innerHTML = `<img src="${produto.imagem}" alt="${produto.nome}" style="max-width:80px; max-height:80px; object-fit:contain;">`;
        }
    }).catch(error => alert(error));
}
window.alterarValoresTabela = alterarValoresTabela;

//Não mexer neste método
const modificaValores = ([produto1, produto2, produto3, produto4, produto5, produto6]) => {

    const tabela = document.getElementById("tabelaProdutos").getElementsByTagName('tbody')[0];
    tabela.rows[0].cells[1].innerText = produto1.preco;
    tabela.rows[0].cells[2].innerText = produto1.estoque;
    tabela.rows[1].cells[1].innerText = produto2.preco;
    tabela.rows[1].cells[2].innerText = produto2.estoque;
    tabela.rows[2].cells[1].innerText = produto3.preco;
    tabela.rows[2].cells[2].innerText = produto3.estoque;
    tabela.rows[3].cells[1].innerText = produto4.preco;
    tabela.rows[3].cells[2].innerText = produto4.estoque;
    tabela.rows[4].cells[1].innerText = produto5.preco;
    tabela.rows[4].cells[2].innerText = produto5.estoque;
    tabela.rows[5].cells[1].innerText = produto6.preco;
    tabela.rows[5].cells[2].innerText = produto6.estoque;

}

window.onload = carregaCarrinho;

