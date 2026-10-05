LAB2 - Programação Concorrente 2026.2

Conteúdo:
- src/python/serial/main.py: versão serial original.
- src/python/concurrent/main.py: threads iniciadas e aguardadas; maior nota calculada pela thread de cada turma.
- src/c/serial/main.c: versão serial original.
- src/c/concurrent/main.c: espera por todas as threads, maior nota por turma e proteção do gerador aleatório.
- comments/comments1.txt: análise serial.
- comments/comments2.txt: memória compartilhada e condições de corrida.
- validation/: logs reais de duas execuções de cada versão em cada linguagem.

Execução Python (dentro de src/python):
bash run.sh S 3 2
bash run.sh C 3 2

Execução C (dentro de src/c, em Linux/WSL com gcc):
bash build.sh S
bash run.sh 3 2
bash build.sh C
bash run.sh 3 2

Os logs de correção continuam mostrando notas durante o trabalho, como no código base. A divulgação consolidada (blocos de registros e maiores notas) só acontece depois que todas as threads terminam.

Entrega:
O enunciado pede ZIP apenas indiretamente pelo pedido do usuário; o formato de submissão indicado no PDF é .tar.gz. Substitua matr1 e matr2 pelas matrículas reais. A partir da pasta que contém Lab2:
tar -czvf lab2_matr1_matr2.tar.gz Lab2/src
bash Lab2/submit-answer.sh lab2 lab2_matr1_matr2.tar.gz

O PDF pede comments na raiz de Lab2, mas manda empacotar somente src. Para evitar perder as respostas no arquivo de submissão, há cópias idênticas dos dois comentários em src/comments. Os arquivos na raiz também foram mantidos.

Não houve criação de repositório privado nem submissão. Confira as exigências e o prazo com o professor antes de entregar.
