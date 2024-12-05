int n = 10;
int[] arr = new int[n];
Random random = new Random();
void FillArray(int[] arr)
{
    int i = 0;
    while (i < arr.Length)
    {
        arr[i] = random.Next(2, 11);
        i++;
    }
}
void PrintArray(int[] arr)
{
    int i = 0;
    while (i < arr.Length)
    {
        Console.Write($"{arr[i]} ");
        i++;
    }
}
FillArray(arr);
PrintArray(arr);
int GetSumOfElements(int[] arr)
{
    int i = 0;
    int sum = 0;
    while (i < arr.Length)
    {
        sum = sum + arr[i];
        i++;
    }
    return sum;
}
Console.Write($"{GetSumOfElements(arr)} ");
double GetCompositionOfElements(int[] arr)
{
    int i = 0;
    double composition = 1;
    while (i < arr.Length)
    {
        composition = composition * arr[i];
        i++;
    }
    return composition;
}
Console.Write($"{GetCompositionOfElements(arr)} ");