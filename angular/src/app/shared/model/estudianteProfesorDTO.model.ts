import { NotaDTO } from "./notaDTO.model";

export interface EstudianteProfesorDTO {
  idUsuario: number;
  nombre?: string;
  apellido?: string;
  email?: string;
  promedio?:number;
  notas?: NotaDTO[];
}

function calcularPromedio(estudiante: EstudianteProfesorDTO): number | null {
  if (!estudiante.notas || estudiante.notas.length === 0) return null;

  // Filtrar solo las notas con nroNota 1 a 4, asegurando que estén definidas y calificacion sea número
  const notasValidas = estudiante.notas.filter(nota =>
    nota.nroNota !== undefined &&
    nota.nroNota >= 1 &&
    nota.nroNota <= 4 &&
    typeof nota.calificacion === "number"
  );

  if (notasValidas.length === 0) return null;

  // Sumar las calificaciones
  const suma = notasValidas.reduce((acc, nota) => acc + (nota.calificacion ?? 0), 0);

  // Dividir por la cantidad de notas válidas (si quieres que siempre sea /4, incluso si faltan, cambia esta línea)
  return suma / 4;
}
