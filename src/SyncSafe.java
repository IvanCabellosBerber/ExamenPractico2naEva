public class SyncSafe
{
    public int Encode(int normalInteger)
    {
        return EncodeOrDecode(normalInteger, Convertion.Encode);
    }

    public int Decode(int syncSafeInteger)
    {
        return EncodeOrDecode(syncSafeInteger, Convertion.Decode);
    }

    int EncodeOrDecode(int number, Convertion type)
    {
        int a, b, c, d, converted = 0;

        switch (type)
        {
            case Encode:
                a = number & 0x7f;
                b = (number >> 7) & 0x7f;
                c = (number >> 14) & 0x7f;
                d = (number >> 21) & 0x7f;

                converted = converted | a;
                converted = converted | (b << 8);
                converted = converted | (c << 16);
                converted = converted | (d << 24);
                break;

            case Decode:
                a = number & 0xff;
                b = (number >> 8) & 0xff;
                c = (number >> 16) & 0xff;
                d = (number >> 24) & 0xff;

                converted = converted | a;
                converted = converted | (b << 7);
                converted = converted | (c << 14);
                converted = converted | (d << 21);
                break;
        }

        return converted;
    }

    public enum Convertion
    {
        Encode,
        Decode
    }
}
