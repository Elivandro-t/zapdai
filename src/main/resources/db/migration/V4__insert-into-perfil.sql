INSERT INTO perfil (perfil_id, name, ativo) VALUES
(1, 'ROLE_USER', true),
(2, 'ROLE_ADMIN', true),
(3, 'ROLE_FUNCIONARIO', true),
(4, 'ROLE_PLANO_PLENO', true),
(5, 'ROLE_PLANO_START', true),
(6, 'ROLE_PLANO_PRO', true),
(7, 'ROLE_PLANO_PLUS', true)
ON CONFLICT (perfil_id) DO NOTHING;
