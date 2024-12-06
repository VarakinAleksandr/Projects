


int [,] CrsateMatrix (int rowCount, int columeCount)
{
    int[,] matrix = new int[rowCount, columeCount];

    Random rnd = new Random();
    for (int i = 0; i < matrix.GetLength(0); i++) //  0  это строки
    {
        for (int j = 0; j < matrix.GetLength(1); j++) // 1 столбцы
        {
            matrix[i, j] = rnd.Next(1, 11);
        }
    }
    return matrix;
}
void ShowMatrix (int[,] matrix)
{
    for (int i = 0; i < matrix.GetLength(0); i++) //  0  это строки
    {
        for (int j = 0; j < matrix.GetLength(1); j++) // 1 столбцы
        {

            Console.Write($"{matrix[i, j]} "); // Интерполяция строк

        }
        Console.WriteLine();
    }
}