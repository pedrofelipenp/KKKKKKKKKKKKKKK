package main

import (
	"fmt"
	"time"
)

func produtorA(c chan<- string) {
	time.Sleep(300 * time.Millisecond)
	c <- "Mensagem do produtor A"
}

func produtorB(c chan<- string) {
	time.Sleep(600 * time.Millisecond)
	c <- "Mensagem do produtor B"
}

func produtorC(c chan<- string) {
	time.Sleep(1000 * time.Millisecond)
	c <- "Mensagem do produtor C"
}

func main() {
	canalA := make(chan string)
	canalB := make(chan string)
	canalC := make(chan string)

	go produtorA(canalA)
	go produtorB(canalB)
	go produtorC(canalC)

	recebeuPrimeira := false

	for !recebeuPrimeira {
		select {
		case mensagem := <-canalA:
			fmt.Println(mensagem)
			recebeuPrimeira = true
		case mensagem := <-canalB:
			fmt.Println(mensagem)
			recebeuPrimeira = true
		case mensagem := <-canalC:
			fmt.Println(mensagem)
			recebeuPrimeira = true
		default:
			fmt.Println("Aguardando...")
			time.Sleep(100 * time.Millisecond)
		}
	}
}
