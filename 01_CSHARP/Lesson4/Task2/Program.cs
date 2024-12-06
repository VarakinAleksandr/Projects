int[,] CrsateMatrix(int rowCount, int columeCount)
{
    int[,] matrix = new int[rowCount, columeCount];

    Random rnd = new Random();
    for (int i = 0; i < matrix.GetLength(0); i++) //  0  это строки
    {
        for (int j = 0; j < matrix.GetLength(1); j++) // 1 столбцы
        {
            matrix[i, j] = rnd.Next(1, 1000);
        }
    }
    return matrix;
}

int[,] matrix = CrsateMatrix(3, 4);
ShowMatrix(matrix);
foreach (int e in matrix)
{
    if (IsInteresting(e) == true)
    {
        Console.WriteLine(e);
    }
}
bool IsInteresting(int value)
{
    int sumInDigits = GetSunInDigits(value);
    if (sumInDigits % 2 == 0)
    {
        return true;
    }
    return false;
}
int GetSunInDigits(int value)
{
    int sum =0;
    while(value>0)
    {
        sum = sum + value % 10;
        value=value/10;
    }
    return sum;
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





