

Console.WriteLine("Введите первое число: ");
int numberone =  Convert.ToInt32(Console.ReadLine());
////Console.WriteLine("Введите второе число: ");
//int numbertwo =  Convert.ToInt32(Console.ReadLine());
if(numberone % 7 ==0 && numberone % 23 ==0)
{
    Console.WriteLine("Да");
}
else
{
    Console.WriteLine("Нет");
}