INSERT INTO funcao (id, name, router, icone, ativo, menu_itens, icone_item) VALUES
  ('funcao-0', 'Home', '/admin', 'home', true, false, false),
  ('funcao-1', 'Dashboard', null, 'bar_chart_4_bars', true, true, true),
  ('funcao-2', 'Clientes', null, 'groups', true, true, true),
  ('funcao-3', 'Produtos', null, 'menu_book', true, true, true),
  ('funcao-4', 'Cadastro', null, 'assignment_ind', true, true, true),
  ('funcao-5', 'Financeiro', null, 'attach_money', true, true, true),
  ('funcao-6', 'Planos', null, 'new_releases', true, true, true),
  ('funcao-7', 'Tarefas', null, 'today', true, true, true),
  ('funcao-8', 'Ações', null, 'pending_actions', true, true, true),
  ('funcao-9', 'Anotações', '/admin/anotacoes', 'assignment', true, false, false),
  ('funcao-10', 'Pedidos', '/admin/pedidos', 'shopping_cart', true, false, false),
  ('funcao-11', 'Entregadores', '/admin/entregadores', 'local_shipping', true, false, false);

-- Inserção de subfunções (children)
INSERT INTO subfuncao (id, name, router, icone, funcao_id) VALUES
  -- Cadastro
  ('sub-4-1', 'Contas a Pagar', '/admin/cadastro/contas-pagar', null, 'funcao-4'),
  ('sub-4-2', 'Boletos', '/admin/cadastro/boletos', null, 'funcao-4'),

  -- Financeiro
  ('sub-5-1', 'Contas a Pagar', '/dashboard/contas/pagar', null, 'funcao-5'),
  ('sub-5-2', 'Contas a Receber', '/dashboard/contas/receber', null, 'funcao-5'),
  ('sub-5-3', 'Boletos', '/dashboard/contas/boletos', null, 'funcao-5'),
  ('sub-5-4', 'Fluxo de Caixa', '/dashboard/financeiro/fluxo-caixa', null, 'funcao-5'),
  ('sub-5-5', 'Relatórios Financeiros', '/dashboard/financeiro/relatorios', null, 'funcao-5'),

  -- Planos
  ('sub-6-1', 'Todos', '/dashboard/planos/all', null, 'funcao-6'),
  ('sub-6-2', 'Plano Básico', '/dashboard/planos/basico', null, 'funcao-6'),
  ('sub-6-3', 'Plano Intermediário', '/dashboard/planos/intermediario', null, 'funcao-6'),
  ('sub-6-4', 'Plano Premium', '/dashboard/planos/premium', null, 'funcao-6'),
  ('sub-6-5', 'Plano Personalizado', '/dashboard/planos/personalizado', null, 'funcao-6'),

  -- Dashboard
  ('sub-1-1', 'Relatórios de Vendas', '/dashboard/relatorios/vendas', null, 'funcao-1'),
  ('sub-1-2', 'Estatísticas de Acesso', '/dashboard/estatisticas', null, 'funcao-1'),
  ('sub-1-3', 'Indicadores de Desempenho', '/dashboard/kpi', null, 'funcao-1'),

  -- Clientes
  ('sub-2-1', 'Cadastro de Cliente', '/admin/clientes/cadastro', null, 'funcao-2'),
  ('sub-2-2', 'Lista de Clientes', '/admin/clientes/lista', null, 'funcao-2'),
  ('sub-2-3', 'Fidelidade', '/admin/clientes/fidelidade', null, 'funcao-2'),

  -- Produtos
  ('sub-3-1', 'Novo Produto', '/admin/produtos/novo', null, 'funcao-3'),
  ('sub-3-2', 'Categorias', '/admin/produtos/categorias', null, 'funcao-3'),
  ('sub-3-3', 'Estoque', '/admin/produtos/estoque', null, 'funcao-3'),

  -- Tarefas
  ('sub-7-1', 'Minhas Tarefas', '/admin/tarefas/minhas', null, 'funcao-7'),
  ('sub-7-2', 'Tarefas da Equipe', '/admin/tarefas/equipe', null, 'funcao-7'),

  -- Ações
  ('sub-8-1', 'Campanhas Ativas', '/admin/acoes/campanhas', null, 'funcao-8'),
  ('sub-8-2', 'Histórico de Ações', '/admin/acoes/historico', null, 'funcao-8');
