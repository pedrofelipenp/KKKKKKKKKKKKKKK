import random
import time
import sys
import threading
from typing import Dict, List, Optional, Any, Tuple


class Classes:

    def __init__(self, num_classes: int, num_students_per_class: int) -> None:
        self.professors: Dict[int, str] = {
            1: "Prof1",
            2: "Prof2"
        }

        self._next_id_counter = 1
        self.class_ids: List[int] = list(range(1, num_classes + 1))
        self.num_students_per_class: int = num_students_per_class

        self.semester_registry: Dict[str, Dict[str, Optional[Any]]] = self._generate_semester_registry()
        
        # Dicionário para armazenar a maior nota de cada turma
        self.highest_grade_per_class: Dict[int, Tuple[str, float]] = {}

    def _generate_student_grade(self) -> Tuple[str, float]:
        grade_value = random.random() * 10
        return (f"{grade_value:0.2f}", grade_value)

    def _generate_student_id(self) -> str:
        sid = self._next_id_counter
        self._next_id_counter += 1
        return str(sid)

    def _generate_semester_registry(self) -> Dict[str, Dict[str, Optional[Any]]]:
        registry: Dict[str, Dict[str, Optional[Any]]] = {}
        for class_id in self.class_ids:
            for _ in range(self.num_students_per_class):
                student_id = self._generate_student_id()
                registry[student_id] = {
                    "class_id": class_id,
                    "final_grade": None
                }
        return registry

    def get_students_in_class(self, class_id: int) -> List[str]:
        return [
            student_id for student_id in self.semester_registry
            if self.semester_registry[student_id]["class_id"] == class_id
        ]

    def process_grades(self, class_id: int) -> None:
        professor = self.professors[(class_id - 1) % len(self.professors) + 1]
        print(f"\n{professor}'s class ({class_id}) — starting grading...")
        time.sleep(random.uniform(0.5, 1.0))

        their_alumni = self.get_students_in_class(class_id)
        
        # Variáveis locais para calcular a maior nota desta turma
        highest_grade_value: float = -1.0
        highest_grade_student: str = ""
        highest_grade_str: str = ""

        for student_id in their_alumni:
            time.sleep(random.uniform(0.2, 0.5))

            grade_str, grade_value = self._generate_student_grade()
            self.semester_registry[student_id]["final_grade"] = grade_str
            
            # Atualiza a maior nota da turma
            if grade_value > highest_grade_value:
                highest_grade_value = grade_value
                highest_grade_student = student_id
                highest_grade_str = grade_str

            print(f"{professor} corrected Student {student_id} from class {class_id} - Grade: {grade_str}")
            time.sleep(random.uniform(0.1, 0.3))

        # Armazena a maior nota da turma (thread-safe: cada thread escreve em chave diferente)
        self.highest_grade_per_class[class_id] = (highest_grade_student, highest_grade_str)
        
        print(f"{professor}'s class {class_id} grades successfully processed!")
        print(f"🏆 {professor}'s class {class_id} HIGHEST GRADE: Student {highest_grade_student} - {highest_grade_str}\n")

    def registry_to_string(self, class_id: int) -> None:
        professor = self.professors[(class_id - 1) % len(self.professors) + 1]
        their_alumni = self.get_students_in_class(class_id)

        print(f"\n*********** {professor}'s Class {class_id} ***********")
        for student_id in their_alumni:
            print(
                f"student_id: {student_id}, "
                f"class_id: {class_id}, "
                f"final_grade: {self.semester_registry[student_id]['final_grade']}"
            )
        
        # Exibe a maior nota da turma
        if class_id in self.highest_grade_per_class:
            student, grade = self.highest_grade_per_class[class_id]
            print(f"🎯 HIGHEST GRADE: Student {student} - {grade}")


if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python main.py <qtd_turmas> <qtd_alunos_por_turma>")
        sys.exit(1)

    num_classes: int = int(sys.argv[1])
    num_students_per_class: int = int(sys.argv[2])

    semester = Classes(num_classes, num_students_per_class)
    semester_desc = "2026.1"

    print(f"======================= Welcome to {semester_desc}'s UFCG Semester =======================")

    threads: List[threading.Thread] = []

    # Cria e inicia as threads
    for class_id in semester.class_ids:
        t = threading.Thread(target=semester.process_grades, args=(class_id,))
        threads.append(t)
        t.start()  # <-- IMPORTANTE: faltava o start() no seu código!

    # Aguarda todas terminarem
    for t in threads:
        t.join()

    # Exibe os resultados
    for class_id in semester.class_ids:
        semester.registry_to_string(class_id)

    print("\n======================= All grades released! =======================")