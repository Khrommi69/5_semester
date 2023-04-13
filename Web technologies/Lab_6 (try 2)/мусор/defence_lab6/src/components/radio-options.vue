<template>
  <div>
    <label v-for="item in options" :key="item.label">
      <input
        type="radio"
        :name="'group'"
        :value="item.value"
        :checked="item.value == value"
        @change="$emit('update:modelValue', $event.target.value)"
      />
      {{ item.label }}
    </label>
  </div>
</template>

<script>
export default {
  name: "RadioOptions",
  props: {
    options: Array,
    modelValue: String,
  },
};
</script>
