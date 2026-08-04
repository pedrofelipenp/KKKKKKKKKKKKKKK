package main

import (
	"fmt"
	"time"
)

func produtorA(c chan<- string) {
	for i := 1; i <= 10; i++ {
		time.Sleep(300 * time.Millisecond)
		c <- fmt.Sprintf("A -> %d", i)
	}
}

func produtorB(c chan<- string) {
	for i := 1; i <= 10; i++ {
		time.Sleep(600 * time.Millisecond)
		c <- fmt.Sprintf("B -> %d", i)
	}
}

func produtorC(c chan<- string) {
	for i := 1; i <= 10; i++ {
		time.Sleep(1000 * time.Millisecond)
		c <- fmt.Sprintf("C -> %d", i)
	}
}

func main() {
	canalA := make(chan string)
	canalB := make(chan string)
	canalC := make(chan string)

	go produtorA(canalA)
	go produtorB(canalB)
	go produtorC(canalC)

	for i := 0; i < 30; i++ {
		select {
		case mensagem := <-canalA:
			fmt.Println(mensagem)
		case mensagem := <-canalB:
			fmt.Println(mensagem)
		case mensagem := <-canalC:
			fmt.Println(mensagem)
		}
	}
}
