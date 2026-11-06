
<template>
  <div class="hello-container">

    <h1>Hello World! 🎉</h1>

    <p>Congratulations, you have logged in successfully!</p>

    <div class="button-container">

      <!-- Return to Login -->
      <button @click="goBack" class="btn">
        Return to login page
      </button>

      <!-- TASK 2: Open Course API -->
      <button @click="openCourseAPI" class="btn">
        Open Course API
      </button>

    </div>

  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

// Return to Login Page
const goBack = () => {
  localStorage.clear()
  router.push('/')
}

// TASK 2: Open Backend API in a new tab
const openCourseAPI = () => {
  window.open(
    'http://localhost:8080/api/courses',
    '_blank',
    'noopener,noreferrer'
  )
}
</script>

<style scoped>
.hello-container {
  padding: 40px;
  text-align: center;
}

.button-container {
  display: flex;
  gap: 15px;
  justify-content: center;
  padding: 20px 0;
}

.btn {
  padding: 8px 16px;
  cursor: pointer;
}
</style>
