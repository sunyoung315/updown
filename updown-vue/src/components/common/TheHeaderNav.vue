<template>
    <header>
        <div class="header-container">
            <RouterLink to="today"><h2>로고</h2></RouterLink> 
            <nav>
                <RouterLink :to="{name: 'today'}">오늘의 기록</RouterLink> | 
                <RouterLink :to="{name: 'record'}">과거 전체 기록</RouterLink>
            </nav>
            <RouterLink to="/">로그아웃</RouterLink>
        </div>
    </header>
</template>

<script setup>

</script>

<style scoped>
header {
    border: 1px solid gray;
}
.header-container {
    display: flex;
    justify-content: space-between;
}
</style>