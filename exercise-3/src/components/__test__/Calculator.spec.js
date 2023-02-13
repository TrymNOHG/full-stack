import { mount } from "@vue/test-utils";
import GuessAge from "@/components/Calculator.vue";
import { assertType, expectTypeOf } from 'vitest'

test("mount component",async()=>{
    expect(GuessAge).toBeTruthy();

    const wrapper = mount(GuessAge,{
        props:{
            title:"Guess User Age App",
        },
    });
    expect(wrapper.text()).toContain("Guess User Age App");
})
