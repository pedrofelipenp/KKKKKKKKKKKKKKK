package main

import "fmt"

func geraNumeros(c chan<- int) {
	for i := 1; i <= 5; i++ {
		c <- i
	}

	close(c)
}

func quadrado(entrada <-chan int, saida chan<- int) {
	for numero := range entrada {
		saida <- numero * numero
	}

	close(saida)
}

func main() {
	entrada := make(chan int)
	saida := make(chan int)

	go geraNumeros(entrada)
	go quadrado(entrada, saida)

	for resultado := range saida {
		fmt.Println(resultado)
	}
}