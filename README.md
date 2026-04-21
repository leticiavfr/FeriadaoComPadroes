# Sistema de E-commerce — Aplicação de Padrões de Projeto

## Visão Geral

Este projeto implementa um sistema de processamento de pedidos em um contexto de comércio, utilizando diversos padrões de projeto (Design Patterns) de forma integrada.

A aplicação simula:

* Criação de pedidos
* Aplicação de regras de preço (descontos e frete)
* Processamento de pagamento
* Validação de operações
* Integração com serviços externos
* Notificação de eventos

O objetivo principal é demonstrar, de forma prática, o uso combinado de padrões para construir um sistema modular, flexível e desacoplado.

## Padrões de Projeto Utilizados

### Strategy

Define diferentes formas de pagamento.

* `PagamentoStrategy`
* `PagamentoPix`
* `PagamentoCartao`
* `PagamentoDinheiro`

---

### Factory Method

Responsável por criar os métodos de pagamento dinamicamente.

* `PagamentoFactory`

---

### Proxy

Controla o acesso ao pagamento, adicionando regras como:

* Validação de método inválido

* Limite de tentativas

* Logs de execução

* `PagamentoProxy`

---

### Adapter

Permite integrar APIs externas com interfaces incompatíveis.

* `CartaoAPI` (classe externa)
* `PagamentoCartaoAdapter`

---

### Singleton

Centraliza configurações globais do sistema.

* `Config` (define, por exemplo, o limite de tentativas)

---

### Decorator

Permite adicionar funcionalidades ao pedido dinamicamente.

Exemplos:

* Aplicação de desconto
* Adição de frete

Classes:

* `PedidoDecorator`
* `Desconto10`
* `FreteExpresso`

---

### Observer

Notifica automaticamente interessados quando o estado do pedido muda.

* `Observer`
* `Email`
* `Sms`

---

### Facade

Simplifica o uso do sistema, encapsulando toda a complexidade em um único ponto de acesso.

* `PedidoFacade`

---

## Fluxo do Sistema

```text
Cliente → Facade → Pedido (Decorator)
                    ↓
                 Strategy (Factory)
                    ↓
                 Proxy
                    ↓
                 Adapter (se necessário)
                    ↓
                 Execução do pagamento
                    ↓
                 Atualização de status
                    ↓
                 Observer (notificações)
```

---

## Estrutura Geral

* **Pedido**: representa o item base do sistema
* **Decorators**: modificam preço e descrição
* **Strategy + Factory**: definem e instanciam o pagamento
* **Proxy**: controla e valida execução
* **Adapter**: integra serviços externos
* **Observer**: reage a mudanças de estado
* **Facade**: orquestra todo o fluxo

---

## Conclusão

O sistema ilustra como diferentes padrões de projeto podem trabalhar em conjunto para resolver problemas comuns de software, resultando em uma arquitetura mais organizada, reutilizável e escalável.

---

## Autor

Projeto desenvolvido para fins acadêmicos, com foco em aprendizado de padrões de projeto.
