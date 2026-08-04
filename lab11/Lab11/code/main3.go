package main

import "fmt"

func produtorA(c chan<- string) {
	for i := 0; i < 5; i++ {
		c <- "Produtor A"
	}
}

func produtorB(c chan<- string) {
	for i := 0; i < 5; i++ {
		c <- "Produtor B"
	}
}

func main() {
	canalA := make(chan string)
	canalB := make(chan string)

	go produtorA(canalA)
	go produtorB(canalB)

	for i := 0; i < 10; i++ {
		select {
		case mensagem := <-canalA:
			fmt.Println(mensagem)
		case mensagem := <-canalB:
			fmt.Println(mensagem)
		}
	}
}
