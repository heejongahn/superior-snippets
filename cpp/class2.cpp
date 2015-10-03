#include <bitset>
#include <iostream>
#include <string>

using namespace std;
class Rtype {
    private:
        bitset<6> opcode;
        bitset<5> rs;
        bitset<5> rt;
        bitset<5> rd;
        bitset<5> sft;
        bitset<6> fn;
    public:
        void SetInfo(bitset<6> _opcode, bitset<5> _rs, bitset<5> _rt, bitset<5> _rd, bitset<5> _sft, bitset<6> _fn) {
            opcode = _opcode;
            rs = _rs;
            rt = _rt;
            rd = _rd;
            sft = _sft;
            fn = _fn;
        }

        string format() {
            return opcode.to_string() + rs.to_string() + rt.to_string() + rd.to_string() + sft.to_string() + fn.to_string();
        }
    };

class Itype {
    private:
        bitset<6> opcode;
        bitset<5> rs;
        bitset<5> rt;
        bitset<16> imm;
    public:
        void SetInfo(bitset<6> _opcode, bitset<5> _rs, bitset<5> _rt, bitset<16> _imm)  {
            opcode = _opcode;
            rs = _rs;
            rt = _rt;
            imm = _imm;
        }

        string format() {
            return opcode.to_string() + rs.to_string() + rt.to_string() + imm.to_string();
        }
    };

class Jtype {
    private:
        bitset<6> opcode;
        bitset<26> target;
    public:
        void SetInfo(bitset<6> _opcode, bitset<26> _target) {
            opcode = _opcode;
            target = _target;
        }

        string format() {
            return opcode.to_string() + target.to_string();
        }
    };

int main() {
    Rtype r;
    r.SetInfo(bitset<6>(10), bitset<5>(3), bitset<5>(4), bitset<5>(4), bitset<5>(0), bitset<6>(22));
    string result = r.format();

    cout << result << '\n';
}
